package br.gov.df.pm.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.df.pm.domain.exception.FotoCandidatoNaoEncontradaException;
import br.gov.df.pm.domain.model.FotoCandidato;
import br.gov.df.pm.domain.repository.CandidatoRepository;
import br.gov.df.pm.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoCandidatoService {
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoCandidato salvar(FotoCandidato foto, InputStream dadosArquivo) {
		
		Long candidatoId = foto.getCandidato().getId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoCandidato> fotoExistente = candidatoRepository
				.findFotoById(candidatoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			candidatoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  candidatoRepository.save(foto);
		candidatoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();

		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoCandidato buscarOuFalhar(Long candidatoId) {
		return candidatoRepository.findFotoById(candidatoId)
				.orElseThrow(() -> new FotoCandidatoNaoEncontradaException(candidatoId));
	}

	@Transactional
	public void excluir(Long candidatoId) {
		FotoCandidato foto = buscarOuFalhar(candidatoId);
		
		candidatoRepository.delete(foto);
		candidatoRepository.flush();

		fotoStorage.remover(foto.getNomeArquivo());
	}

}
