package br.gov.df.pm.api.model.input;

import javax.persistence.Column;
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
	private String telefone;
	
	@Column(nullable = false)
	private Integer ativo;
	
	private String localidade;
	
	private String descricao;
	
	@NotBlank
	@Email
	private String email;

}
