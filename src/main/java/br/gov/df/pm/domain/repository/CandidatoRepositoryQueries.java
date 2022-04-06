package br.gov.df.pm.domain.repository;

import br.gov.df.pm.domain.model.FotoCandidato;

public interface CandidatoRepositoryQueries {
	
	FotoCandidato save(FotoCandidato foto);
	
	void delete(FotoCandidato foto);

}
