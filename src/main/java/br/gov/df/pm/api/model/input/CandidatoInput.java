package br.gov.df.pm.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoInput {
	
	@NotBlank
	private String nome;
	private String apelido;
	
	@NotNull
	private Integer numero;
	
	@NotBlank
	private String partido;
	
	@NotBlank
	private String telefone;
	private String descricao;
	private String localidade;
	
	@NotBlank
	@Email
	private String email;

}
