package br.gov.df.pm.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.df.pm.domain.model.Usuario;

public interface UsuarioRepository
		extends CustomJpaRepository<Usuario, Long>, 
		UsuarioRepositoryQueries, 
		JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByEmail(String email);



}
