package br.gov.df.pm.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.gov.df.pm.domain.repository.CandidatoRepositoryQueries;

@Repository
public class CandidatoRepositoryImpl implements CandidatoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;



}
