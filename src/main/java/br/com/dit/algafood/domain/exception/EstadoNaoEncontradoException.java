package br.com.dit.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(Long estadoId) {
        this("Não existe cadastro de estado para o id " + estadoId);
    }
    public EstadoNaoEncontradoException(String message) {
        super(message);
    }
}
