package br.gov.df.pm.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoCandidatoModel {
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

}
