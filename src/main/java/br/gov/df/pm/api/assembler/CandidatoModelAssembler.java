package br.gov.df.pm.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.df.pm.api.model.CandidatoModel;
import br.gov.df.pm.domain.model.Candidato;


@Component
public class CandidatoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CandidatoModel toModel(Candidato candidato) {
		
		return modelMapper.map(candidato, CandidatoModel.class);
	}
	
	public List<CandidatoModel> toCollectionModel(List<Candidato> candidatos) {
		return candidatos.stream()
				.map(candidato -> toModel(candidato))
				.collect(Collectors.toList());
	}
}
