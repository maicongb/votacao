package br.gov.df.pm.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.pm.api.ResourceUriHelper;
import br.gov.df.pm.api.assembler.CandidatoInputDisassembler;
import br.gov.df.pm.api.assembler.CandidatoModelAssembler;
import br.gov.df.pm.api.model.CandidatoModel;
import br.gov.df.pm.api.model.input.CandidatoInput;
import br.gov.df.pm.domain.exception.CandidatoNaoEncontradoException;
import br.gov.df.pm.domain.exception.NegocioException;
import br.gov.df.pm.domain.model.Candidato;
import br.gov.df.pm.domain.repository.CandidatoRepository;
import br.gov.df.pm.domain.repository.filter.CandidatoFilter;
import br.gov.df.pm.domain.service.CadastroCandidatoService;
import br.gov.df.pm.infrastructure.repository.spec.CandidatoSpecs;

@RestController
@RequestMapping(path = "/candidato", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidatoController {
	
	@Autowired
	private CadastroCandidatoService cadastroCandidato;
	
	@Autowired
	private CandidatoModelAssembler candidatoModelAssembler;
	
	@Autowired
	private CandidatoInputDisassembler candidatoInputDisassembler;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@GetMapping
	public Page<CandidatoModel> listar(CandidatoFilter filtro,
			@PageableDefault(size=10) Pageable pageable) {
		
		Page<Candidato> candidatoPage = 
				candidatoRepository.findAll(CandidatoSpecs.usandoFiltro(filtro), pageable);
		
		List<CandidatoModel> candidatoModel = candidatoModelAssembler
				.toCollectionModel(candidatoPage.getContent());
		
		Page<CandidatoModel> candidatoModelPage = new PageImpl<>(candidatoModel, 
				pageable, candidatoPage.getTotalElements());
		
		return candidatoModelPage;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoModel adicionar(@RequestBody @Valid CandidatoInput candidatoInput) {
		
		Candidato candidato = candidatoInputDisassembler
				.toDomainObject(candidatoInput);
		
		candidato = cadastroCandidato.salvar(candidato);
		
		CandidatoModel candidatoModel = candidatoModelAssembler.toModel(candidato);
		
		//ADICIONAR A URI http://localhost:8080/marca-veiculo/1 NO HEADER
		ResourceUriHelper.addUriInResponseHeader(candidatoModel.getId());
		
		return candidatoModel;
		
	}
	
	@PutMapping("/{candidatoId}")
	public CandidatoModel atualizar(@RequestBody @Valid CandidatoInput candidatoInput,
				@PathVariable Long candidatoId) {
		
		try {
			
			Candidato candidatoAtual = cadastroCandidato
					.buscarOuFalhar(candidatoId);
			
			candidatoInputDisassembler.copyToDomainObject(candidatoInput, candidatoAtual);
			
			candidatoAtual = cadastroCandidato.salvar(candidatoAtual);
			
			return candidatoModelAssembler.toModel(candidatoAtual);
			
		} catch (CandidatoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{candidatoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long candidatoId) {
		cadastroCandidato.excluir(candidatoId);
	}

}
