package br.gov.df.pm.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.df.pm.api.model.input.UsuarioInput;
import br.gov.df.pm.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(
			UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInput usuarioInput, 
			Usuario usuario) {
		
		modelMapper.map(usuarioInput, usuario);
	}

}
