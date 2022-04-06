package br.gov.df.pm.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.df.pm.domain.model.FotoCandidato;
import br.gov.df.pm.domain.model.FotoCandidatoModel;
@Component
public class FotoCandidatoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoCandidatoModel toModel(FotoCandidato foto) {
		return modelMapper.map(foto, FotoCandidatoModel.class);
	}
}
