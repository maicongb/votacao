package br.gov.df.pm.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.df.pm.api.model.input.CandidatoInput;
import br.gov.df.pm.domain.model.Candidato;

@Component
public class CandidatoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Candidato toDomainObject(
			CandidatoInput candidatoInput) {
		return modelMapper.map(candidatoInput, Candidato.class);
	}
	
	public void copyToDomainObject(CandidatoInput candidatoInput, 
			Candidato candidato) {
		
		modelMapper.map(candidatoInput, candidato);
	}

}
