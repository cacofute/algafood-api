package br.com.dit.algafood.domain.exception;

public class EstadoVazioNoBancoDeDadosException extends EntidadeVazioException {
	
	private static final long serialVersionUID = 1L;

    public EstadoVazioNoBancoDeDadosException() {
        super("O estado informado não existe no banco de dados");
    }

}
