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
	private String telefone;
	private Integer ativo;
	private String localidade;
	private String descricao;
	private String email;

}
