package br.gov.df.pm.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String telefone;
	
	@NotNull
	private Integer ativo;
	
	@NotBlank
	private String email;

}
