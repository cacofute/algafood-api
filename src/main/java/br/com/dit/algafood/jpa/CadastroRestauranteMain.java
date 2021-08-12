package br.com.dit.algafood.jpa;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.dit.algafood.AlgafoodApiApplication;
import br.com.dit.algafood.domain.repositories.RestauranteRepository;


public class CadastroRestauranteMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = ctx.getBean(RestauranteRepository.class);
		
		restaurantes.findAll().forEach( r -> {
			System.out.println(r);
			System.out.println(r.getCozinha().getNome());
		});
		
		((ConfigurableApplicationContext)ctx).close();
	}

}
