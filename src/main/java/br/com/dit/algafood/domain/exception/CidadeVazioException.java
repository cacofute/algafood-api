package br.com.dit.algafood.domain.exception;

public class CidadeVazioException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
    public CidadeVazioException() {
        super("Cidade não pode ser vazio");
    }

}
