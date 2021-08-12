package br.com.dit.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.dit.algafood.AlgafoodApiApplication;
import br.com.dit.algafood.domain.repositories.PermissaoRepository;

public class PermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = ctx.getBean(PermissaoRepository.class);
		//permissaoRepository.all().forEach(p -> System.out.println(p));
		((ConfigurableApplicationContext)ctx).close();
	}
}
