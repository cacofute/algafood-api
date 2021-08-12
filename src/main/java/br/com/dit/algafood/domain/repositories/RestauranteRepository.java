package br.com.dit.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dit.algafood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQuery{
	

}
