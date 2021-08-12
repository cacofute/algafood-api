package br.com.dit.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dit.algafood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long>, CozinhaRepositoryQuery{
	
	
}
