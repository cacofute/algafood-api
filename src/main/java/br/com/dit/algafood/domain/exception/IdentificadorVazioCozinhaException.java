package br.com.dit.algafood.domain.exception;


public class IdentificadorVazioCozinhaException extends IdentificadorVazioEntidadeException {
	
	private static final long serialVersionUID = 1L;

    public IdentificadorVazioCozinhaException() {
        super("Identificador da cozinha não pode estar vazio");
    }
}
