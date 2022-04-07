package br.gov.df.pm.domain.model;

import java.time.LocalDate;
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
@Entity(name = "ENQUETE")
public class Enquete {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = true)
	private String descricao;
	
	@Column(nullable = false)
	private LocalDate dataInicio;
	
	@Column(nullable = false)
	private LocalDate dataFim;
	
	@Column(nullable = false)
	private int ativo;

	@OneToMany(mappedBy = "enquete")
	private List<CandidatoEnquete> candidatoEnquetes = new ArrayList<>();
	
}
