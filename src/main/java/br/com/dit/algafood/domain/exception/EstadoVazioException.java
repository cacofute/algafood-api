package br.com.dit.algafood.domain.exception;

public class EstadoVazioException extends EntidadeVazioException {
	
	private static final long serialVersionUID = 1L;

    public EstadoVazioException() {
        super("Estado não pode ser vazio");
    }

}
