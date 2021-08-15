package br.com.dit.algafood.domain.exception;

public class EstadoVazioException extends EntidadeVaziaException {
	
	private static final long serialVersionUID = 1L;

    public EstadoVazioException() {
        super("Estado não pode ser vazio");
    }

}
