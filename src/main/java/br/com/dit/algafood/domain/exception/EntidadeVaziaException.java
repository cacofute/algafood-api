package br.com.dit.algafood.domain.exception;

public abstract class EntidadeVaziaException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public EntidadeVaziaException(String message) {
		super(message);
	}	
	

}
