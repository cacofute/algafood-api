package br.com.dit.algafood.domain.repositories;

import java.util.List;

import br.com.dit.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQuery {

	List<Restaurante> findAll();
	
	
}
