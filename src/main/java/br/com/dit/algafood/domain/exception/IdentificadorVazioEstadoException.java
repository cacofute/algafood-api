package br.com.dit.algafood.domain.exception;


public class IdentificadorVazioEstadoException extends IdentificadorVazioEntidadeException {
	
	private static final long serialVersionUID = 1L;

    public IdentificadorVazioEstadoException() {
        super("Identificador do estado não pode estar vazio");
    }
}
