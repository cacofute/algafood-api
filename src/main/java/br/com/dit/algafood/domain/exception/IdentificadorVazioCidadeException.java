package br.com.dit.algafood.domain.exception;


public class IdentificadorVazioCidadeException extends IdentificadorVazioEntidadeException {

	private static final long serialVersionUID = 1L;

	public IdentificadorVazioCidadeException() {
        super("Identificador da cidade não pode estar vazio");
    }
}
