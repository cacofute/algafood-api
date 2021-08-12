package br.com.dit.algafood.jpa;




import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import br.com.dit.algafood.AlgafoodApiApplication;
import br.com.dit.algafood.domain.model.Cozinha;
import br.com.dit.algafood.domain.repositories.CozinhaRepository;

public class InclusaoCozinhaMain {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = 
				new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		Cozinha c1 = new Cozinha();
		c1.setNome("Brasileira");
		
		Cozinha c2 = new Cozinha();
		c2.setNome("Japonesa");
		
		CozinhaRepository cozinhas = ctx.getBean(CozinhaRepository.class);
		
		c1 = cozinhas.save(c1);
		c2 = cozinhas.save(c2);
		
		System.out.println(c1.getId());
		System.out.println(c2.getId());
		
//		CadastroCozinha cadastro = ctx.getBean(CadastroCozinha.class);
//		List<Cozinha> cozinhas = cadastro.listar();
//		for (Cozinha cozinha : cozinhas) {
//			System.out.println(cozinha.getNome());
//		}
		
		
		
	}

}
