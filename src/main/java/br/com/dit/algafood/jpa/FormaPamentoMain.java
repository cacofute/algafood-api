package br.com.dit.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.dit.algafood.AlgafoodApiApplication;
import br.com.dit.algafood.domain.repositories.FormaPagamentoRepository;

public class FormaPamentoMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = ctx.getBean(FormaPagamentoRepository.class);
		formaPagamentoRepository.findAll().forEach( f -> System.out.println(f));
	}

}
