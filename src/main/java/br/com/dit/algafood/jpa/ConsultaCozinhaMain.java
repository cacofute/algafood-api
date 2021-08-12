package br.com.dit.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.dit.algafood.AlgafoodApiApplication;
import br.com.dit.algafood.domain.model.Cozinha;
import br.com.dit.algafood.domain.repositories.CozinhaRepository;

public class ConsultaCozinhaMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = ctx.getBean(CozinhaRepository.class);
		for (Cozinha cozinha : cozinhas.findAll()) {
			System.out.println(cozinha.getNome());
		}
		
		
		
	}

}
