package br.gov.df.pm.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	private Long id;
	private String nome;
	private String telefone;
	private Integer ativo;
	private String email;
	

}
