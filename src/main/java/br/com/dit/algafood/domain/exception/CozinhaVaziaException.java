package br.com.dit.algafood.domain.exception;

public class CozinhaVaziaException extends EntidadeVaziaException {

	private static final long serialVersionUID = 1L;
	
    public CozinhaVaziaException() {
        super("Cozinha não pode ser vazio");
    }

}
