package br.com.dit.algafood.domain.exception;

public abstract class EntidadeVazioException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public EntidadeVazioException(String message) {
		super(message);
	}	
	

}
