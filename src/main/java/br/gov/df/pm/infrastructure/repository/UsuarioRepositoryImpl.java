package br.gov.df.pm.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.gov.df.pm.domain.repository.UsuarioRepositoryQueries;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;




}
