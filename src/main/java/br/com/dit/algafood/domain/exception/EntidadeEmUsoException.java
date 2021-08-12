package br.com.dit.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntidadeEmUsoException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String entidadeNome, Long entidadeId) {
		this(String.format("%s de código %d não pode ser removido, pois está em uso", entidadeNome, entidadeId));
	}

	public EntidadeEmUsoException(String message) {
		super(message);
	}
	
}
