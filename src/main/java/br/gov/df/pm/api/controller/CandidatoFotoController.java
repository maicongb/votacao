package br.gov.df.pm.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.gov.df.pm.api.assembler.FotoCandidatoModelAssembler;
import br.gov.df.pm.api.model.input.FotoCandidatoInput;
import br.gov.df.pm.domain.exception.EntidadeNaoEncontradaException;
import br.gov.df.pm.domain.model.Candidato;
import br.gov.df.pm.domain.model.FotoCandidato;
import br.gov.df.pm.domain.model.FotoCandidatoModel;
import br.gov.df.pm.domain.service.CadastroCandidatoService;
import br.gov.df.pm.domain.service.CatalogoFotoCandidatoService;
import br.gov.df.pm.domain.service.FotoStorageService;

@RestController
@RequestMapping(path = "/candidato/{candidatoId}/foto")
public class CandidatoFotoController {
	
	@Autowired
	private CatalogoFotoCandidatoService catalogoFotoCandidato;
	
	@Autowired
	private CadastroCandidatoService cadastroCandidato;
	
	@Autowired
	private FotoCandidatoModelAssembler fotoCandidatoModelAssembler;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoCandidatoModel atualizarFoto(@PathVariable Long candidatoId,
			@Valid FotoCandidatoInput fotoCandidatoInput) throws IOException {
	
		Candidato candidato = cadastroCandidato.buscarOuFalhar(candidatoId);
		
		MultipartFile arquivo = fotoCandidatoInput.getArquivo();
		
		FotoCandidato foto = new FotoCandidato();
		foto.setCandidato(candidato);
		foto.setDescricao(fotoCandidatoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoCandidato fotoSalva = catalogoFotoCandidato.salvar(foto, arquivo.getInputStream());
		
		return fotoCandidatoModelAssembler.toModel(fotoSalva);
//		var arquivoFoto = Path.of("C:\\Users\\Maicon\\Desktop\\fotos", nomeArquivo);
	
	}
	
	//DELETAR FOTO DO BANCO DE DADOS E DO ARQUIVO
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long candidatoId) {
		catalogoFotoCandidato.excluir(candidatoId);
	}
	
	//buscar dados da foto
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoCandidatoModel buscar(@PathVariable Long candidatoId) {
		FotoCandidato fotoCandidato = catalogoFotoCandidato.buscarOuFalhar(candidatoId);
		
		return fotoCandidatoModelAssembler.toModel(fotoCandidato);
	}
	
	
	//buscar imagem e servir para a API
	//API DEVERA PASSAR O ACCEPT
	@GetMapping
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long candidatoId, 
			@RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
			FotoCandidato fotoProduto = catalogoFotoCandidato.buscarOuFalhar(candidatoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
}
