package br.gov.df.pm.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "CANDIDATO")
public class Candidato {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String apelido;
	
	@Column(nullable = false)
	private Integer numero;
	
	@Column(nullable = true)
	private String telefone;
	
	@Column(nullable = false)
	private Integer ativo;
	
	@Column(nullable = true)
	private String localidade;
	
	@Column(nullable = true)
	private String descricao;
	
	@Column(nullable = true)
	private String email;
	
	@OneToMany(mappedBy = "candidato")
	private List<CandidatoEnquete> candidatoEnquetes = new ArrayList<>();
}
