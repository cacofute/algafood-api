package br.com.dit.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.dit.algafood.core.validation.ValidacaoException;
import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.exception.EntidadeVaziaException;
import br.com.dit.algafood.domain.exception.IdentificadorVazioEntidadeException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String ERRO_MSG_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno no sistema. Tente novamente e se o problema persistir, "
			+ "entre em contato com o administrador do sistema";
	
	private MessageSource messageSource;

	public ApiExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex); 
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		}
		else if(rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String detail			= "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)	
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	
	}
	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}			
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail   = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)	
			.build();
	
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request){	
		
		HttpStatus status       = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail			= ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ex.getMessage())
			.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(IdentificadorVazioEntidadeException.class)
	public ResponseEntity<?> handleIdentificadorVazioEntidade(IdentificadorVazioEntidadeException ex, WebRequest request){
		HttpStatus status       = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.IDENTIFICADOR_VAZIO;
		String detail 			= ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ex.getMessage())
			.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex 
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeVaziaException.class)
	public ResponseEntity<?> handleEntidadeVazia(EntidadeVaziaException ex, WebRequest request){
		HttpStatus status       = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ENTIDADE_VAZIA;
		String detail 			= ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ex.getMessage())
			.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request){
		HttpStatus status       = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail 			= ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ex.getMessage())
			.build();
		
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
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<Object> handleValidacao(ValidacaoException ex, WebRequest request){
		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
		
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptions(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;		
		String details  = ERRO_MSG_GENERICA_USUARIO_FINAL;		
		Problem problem = createProblemBuilder(status, problemType, details).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {		
		if(body == null) {
			body = createProblemBuilder(status, null, null)
				.title(status.getReasonPhrase())
				.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)
				.build();
		}
		else if(body instanceof String) {			
			body = createProblemBuilder(status, null, null)
				.title((String) body)
				.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)
				.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;		
		
		List<Problem.Field> problemFields = getProblemFields(bindingResult);
		
		String detail   = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(detail)
			.fields(problemFields)
			.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	/**
	 * 
	 * @param bindingResult
	 * @return
	 */
	private List<Problem.Field> getProblemFields(BindingResult bindingResult) {
		List<Problem.Field> problemFields = bindingResult.getAllErrors().stream()
			.map( objectError -> { 
				String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
				
				String name = objectError.getObjectName();
				
				if(objectError instanceof FieldError) {
					name = ((FieldError) objectError ).getField();
				}
				return Problem.Field.builder()
					.name(name)
					.userMessage(message)
					.build(); 
			})
			.collect(Collectors.toList());
		return problemFields;
	}
	
	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String propertyName = ex.getPropertyName();
		String object		= ex.getReferringClass().getSimpleName();
		String allowedProperties = ex.getKnownPropertyIds().stream()
			.map(obj -> obj.toString())
			.collect(Collectors.joining(", "));
				 
		String detail = String.format("A propriedade %s não é permitida ou não existe no objeto %s. "
				+ "Propriedades permitidas (%s)",
				propertyName, object, allowedProperties);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)	
			.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	/**
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
			.map( r -> r.getFieldName())
			.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.CORPO_DA_MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'.", 
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)	
			.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request){		
		
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;		
		String requiredType     = ex.getRequiredType().getSimpleName();
		String parameterName    = ex.getParameter().getParameterName();
		String receivedValue    = ex.getValue().toString();
		
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
				+ "Corrija e informe um valor compatível com o tipo '%s'", parameterName, receivedValue, requiredType);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(ERRO_MSG_GENERICA_USUARIO_FINAL)	
			.build();	
		
		return handleExceptionInternal(ex, problem, headers, status, request);
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
			.detail(detail)
			.timestamp(LocalDateTime.now());
	}
}
