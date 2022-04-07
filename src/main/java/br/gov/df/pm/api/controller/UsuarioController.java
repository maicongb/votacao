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
import br.gov.df.pm.api.assembler.UsuarioInputDisassembler;
import br.gov.df.pm.api.assembler.UsuarioModelAssembler;
import br.gov.df.pm.api.model.UsuarioModel;
import br.gov.df.pm.api.model.input.UsuarioInput;
import br.gov.df.pm.domain.exception.NegocioException;
import br.gov.df.pm.domain.exception.UsuarioNaoEncontradoException;
import br.gov.df.pm.domain.model.Usuario;
import br.gov.df.pm.domain.repository.UsuarioRepository;
import br.gov.df.pm.domain.repository.filter.UsuarioFilter;
import br.gov.df.pm.domain.service.CadastroUsuarioService;
import br.gov.df.pm.infrastructure.repository.spec.UsuarioSpecs;

@RestController
@RequestMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<UsuarioModel> listar(UsuarioFilter filtro,
			@PageableDefault(size=10) Pageable pageable) {
		
		Page<Usuario> usuarioPage = 
				usuarioRepository.findAll(UsuarioSpecs.usandoFiltro(filtro), pageable);
		
		List<UsuarioModel> usuarioModel = usuarioModelAssembler
				.toCollectionModel(usuarioPage.getContent());
		
		Page<UsuarioModel> usuarioModelPage = new PageImpl<>(usuarioModel, 
				pageable, usuarioPage.getTotalElements());
		
		return usuarioModelPage;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		
		Usuario usuario = usuarioInputDisassembler
				.toDomainObject(usuarioInput);
		
		usuario = cadastroUsuario.salvar(usuario);
		
		UsuarioModel usuarioModel = usuarioModelAssembler.toModel(usuario);
		
		//ADICIONAR A URI http://localhost:8080/marca-veiculo/1 NO HEADER
		ResourceUriHelper.addUriInResponseHeader(usuarioModel.getId());
		
		return usuarioModel;
		
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@RequestBody @Valid UsuarioInput usuarioInput,
				@PathVariable Long usuarioId) {
		
		try {
			
			Usuario usuarioAtual = cadastroUsuario
					.buscarOuFalhar(usuarioId);
			
			usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
			
			usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
			
			return usuarioModelAssembler.toModel(usuarioAtual);
			
		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
		cadastroUsuario.excluir(usuarioId);
	}

}
