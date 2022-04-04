package br.gov.df.pm.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoModel {
	
	private Long id;
	private String nome;
	private String apelido;
	private Integer numero;
	private String partido;
	private String telefone;
	private String descricao;
	private String localidade;
	

}
