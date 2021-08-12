package br.com.dit.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarIdentificadorVazioEntidadeException(NegocioException ex, WebRequest request){
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String mensagem = "";
		if(body == null) {
			mensagem = status.getReasonPhrase();
		}
		else if(body instanceof String) {
			mensagem = (String) body;
		}
		body = Problema.builder()
			.dataHora(LocalDateTime.now())
			.mensagem(mensagem)
			.build();
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	

}
