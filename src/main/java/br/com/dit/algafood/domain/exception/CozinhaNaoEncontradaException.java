package br.com.dit.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this("Não existe cadastro de cozinha para o id " + cozinhaId);
	}

	public CozinhaNaoEncontradaException(String message) {
		super(message);
	}
}
