package br.com.dit.algafood.domain.exception;


public class IdentificadorVazioRestauranteException extends IdentificadorVazioEntidadeException {
	
	private static final long serialVersionUID = 1L;

    public IdentificadorVazioRestauranteException() {
        super("Identificador do restaurante não pode estar vazio");
    }
}
