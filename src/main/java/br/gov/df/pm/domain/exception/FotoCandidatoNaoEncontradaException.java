package br.gov.df.pm.domain.exception;

public class FotoCandidatoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoCandidatoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FotoCandidatoNaoEncontradaException(Long candidatoId) {
		this(String.format("Não existe um cadastro de foto do candidato com código %d",	candidatoId));
	}

}