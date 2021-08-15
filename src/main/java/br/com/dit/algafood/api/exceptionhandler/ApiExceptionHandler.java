package br.com.dit.algafood.api.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.exception.EntidadeVaziaException;
import br.com.dit.algafood.domain.exception.IdentificadorVazioEntidadeException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex); 
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		else if(rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String detail			= "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		Problem problem         = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	
	}
	
	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String propertyName = ex.getPropertyName();
		String object		= ex.getReferringClass().getSimpleName();
		String allowedProperties = ex.getKnownPropertyIds().stream()
			.map(obj -> obj.toString())
			.collect(Collectors.joining(", "));
				 
		String detail = String.format("A propriedade %s não é permitida ou não existe no objeto %s. Propriedades permitidas (%s)",
				propertyName, object, allowedProperties);
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
			.map( r -> r.getFieldName())
			.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'.", 
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){	
		
		HttpStatus status       = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail			= ex.getMessage();
		Problem problem         = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(IdentificadorVazioEntidadeException.class)
	public ResponseEntity<?> handleIdentificadorVazioEntidadeException(IdentificadorVazioEntidadeException ex, WebRequest request){
		HttpStatus status       = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.IDENTIFICADOR_VAZIO;
		String detail 			= ex.getMessage();
		Problem problem         = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeVaziaException.class)
	public ResponseEntity<?> handleEntidadeVaziaException(EntidadeVaziaException ex, WebRequest request){
		HttpStatus status       = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ENTIDADE_VAZIA;
		String detail 			= ex.getMessage();
		Problem problem         = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		HttpStatus status       = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail 			= ex.getMessage();
		Problem problem         = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		}
		else if(body instanceof String) {
			body = Problem.builder()
				.title ((String) body)
				.status(status.value())
				.build();	
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	/**
	 * 
	 * @param status
	 * @param problemType
	 * @param detail
	 * @return
	 */
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		return Problem.builder()
			.status(status.value())
			.type  (problemType.getUri())
			.title (problemType.getTitle())
			.detail(detail);
	}
}
