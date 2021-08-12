package br.com.dit.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(Long cidadeId) {
        this("Não existe cadastro de cidade para o id " + cidadeId);
    }

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }
}
