package br.com.dit.algafood.core.validation;

import org.springframework.validation.BeanPropertyBindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;	
	private BeanPropertyBindingResult bindingResult;


}
