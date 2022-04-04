package br.gov.df.pm.domain.exception;

public class CandidatoNaoEncontradoException extends EntidadeNaoEncontradaException  {

	private static final long serialVersionUID = 1L;

	public CandidatoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CandidatoNaoEncontradoException(Long candidatoId) {
		this(String.format("Não existe um cadastro de candidato com código %d", candidatoId));
	}

}
