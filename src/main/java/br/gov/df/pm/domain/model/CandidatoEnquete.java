package br.gov.df.pm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "CANDIDATOENQUETE")
public class CandidatoEnquete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Enquete enquete;
	
	@Column(nullable = false)
	private String numeroPartido;
	

	
}
