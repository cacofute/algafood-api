package br.com.dit.algafood.infrastructure.repositorie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Repository;

import br.com.dit.algafood.domain.model.Restaurante;
import br.com.dit.algafood.domain.repositories.RestauranteRepositoryQuery;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	public RestauranteRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Restaurante> findAll(){
		TypedQuery<Restaurante> query = manager
				.createQuery("FROM Restaurante r JOIN FETCH r.cozinha LEFT JOIN FETCH r.formasPagamento", 
						Restaurante.class);
		return query.getResultList(); 
	}

}
