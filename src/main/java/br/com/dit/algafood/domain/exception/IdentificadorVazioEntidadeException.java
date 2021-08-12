package br.com.dit.algafood.domain.exception;

public abstract class IdentificadorVazioEntidadeException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public IdentificadorVazioEntidadeException(String message) {
		super(message);
	}


}
