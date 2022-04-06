package br.gov.df.pm.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.gov.df.pm.domain.model.Candidato;
import br.gov.df.pm.domain.model.FotoCandidato;

public interface CandidatoRepository extends 
				CustomJpaRepository<Candidato, Long>,
				CandidatoRepositoryQueries,
						JpaSpecificationExecutor<Candidato> {

	Optional<Candidato> findByTelefone(String telefone);
	
	@Query("select f from FotoCandidato f "
			+ "where f.candidato.id = :candidatoId")
	Optional<FotoCandidato> findFotoById(Long candidatoId);

}
