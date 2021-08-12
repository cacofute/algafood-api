package br.com.dit.algafood.domain.exception;

public class RestauranteVazioException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

    public RestauranteVazioException() {
        super("Restaurante não pode ser vazio");
    }

}
