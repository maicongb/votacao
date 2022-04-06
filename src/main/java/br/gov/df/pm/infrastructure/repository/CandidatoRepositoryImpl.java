package br.gov.df.pm.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.df.pm.domain.model.FotoCandidato;
import br.gov.df.pm.domain.repository.CandidatoRepositoryQueries;

@Repository
public class CandidatoRepositoryImpl implements CandidatoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoCandidato save(FotoCandidato foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void delete(FotoCandidato foto) {
		manager.remove(foto);
		
	}
}
