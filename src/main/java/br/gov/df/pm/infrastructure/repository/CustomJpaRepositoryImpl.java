package br.gov.df.pm.infrastructure.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.gov.df.pm.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
implements CustomJpaRepository<T, ID> {

		private EntityManager manager;
		
		public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, 
				EntityManager entityManager) {
			super(entityInformation, entityManager);
			
			this.manager = entityManager;
		}
	
		@Override
		public void detach(T entity) {
			manager.detach(entity);
		}

}