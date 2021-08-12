package br.com.dit.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.dit.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		String sql = "FROM Cozinha c";
		TypedQuery<Cozinha> query =  manager.createQuery(sql, Cozinha.class);
		return query.getResultList();
	}
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

}
