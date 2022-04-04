package br.gov.df.pm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "CANDIDATO")
public class Candidato {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "can_Codigo")
	private Long id;
	
	@Column(name = "can_Nome", nullable = false)
	private String nome;
	
	@Column(name = "can_Apelido", nullable = true)
	private String apelido;
	
	@Column(name = "can_Partido", nullable = false)
	private String partido;
	
	@Column(name = "can_Numero", nullable = false)
	private Integer numero;
	
	@Column(name = "can_Telefone", nullable = true)
	private String telefone;
	
	@Column(name = "can_Ativo", nullable = false)
	private Integer ativo = 1;
	
	@Column(name = "can_Localidade", nullable = true)
	private String localidade;
	
	@Column(name = "can_Descricao", nullable = true)
	private String descricao;
	
//	@ManyToOne
//	@JoinColumn(name = "tve_Codigo", nullable = false)
//	private TipoVeiculo tipoVeiculo;
}
