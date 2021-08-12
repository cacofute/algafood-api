package br.com.dit.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(Long restauranteId) {
		this("Não existe cadastro de restaurante para o id " + restauranteId);
	}

	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}
}
