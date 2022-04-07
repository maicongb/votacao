package br.gov.df.pm.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.df.pm.domain.exception.CandidatoNaoEncontradoException;
import br.gov.df.pm.domain.exception.EntidadeEmUsoException;
import br.gov.df.pm.domain.exception.NegocioException;
import br.gov.df.pm.domain.model.Candidato;
import br.gov.df.pm.domain.repository.CandidatoRepository;

@Service
public class CadastroCandidatoService {
	
	private static final String MSG_CANDIDATO_EM_USO 
	= "Candidato de código %d não pode ser removido ou inativado, pois está em uso";
	
	@Autowired
	private CandidatoRepository candidatoRepository;

	@Transactional
	public Candidato salvar(Candidato candidato) {
		
		candidatoRepository.detach(candidato);
		
		Optional<Candidato> candidatoExistente = candidatoRepository.findByEmail(candidato.getEmail());
		
		if (candidatoExistente.isPresent() && !candidatoExistente.get().equals(candidato)) {
			throw new NegocioException(
					String.format("Já existe um candidato cadastrado com o e-mail %s", candidato.getEmail()));
		}
		
		return candidatoRepository.save(candidato);
	}

	public Candidato buscarOuFalhar(Long candidatoId) {
		return candidatoRepository.findById(candidatoId)
				.orElseThrow(() -> new CandidatoNaoEncontradoException(candidatoId));
				
	}

	@Transactional
	public void excluir(Long candidatoId) {

		try {
			candidatoRepository.deleteById(candidatoId);
			candidatoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CandidatoNaoEncontradoException(candidatoId);
			
		} catch (DataIntegrityViolationException e) {	
			throw new EntidadeEmUsoException(
					String.format(MSG_CANDIDATO_EM_USO , candidatoId));
		}
		
	}

}
