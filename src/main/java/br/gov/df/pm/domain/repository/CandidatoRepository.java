package br.gov.df.pm.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.df.pm.domain.model.Candidato;

public interface CandidatoRepository extends 
				CustomJpaRepository<Candidato, Long>,
						JpaSpecificationExecutor<Candidato> {

	Optional<Candidato> findByTelefone(String telefone);

}
