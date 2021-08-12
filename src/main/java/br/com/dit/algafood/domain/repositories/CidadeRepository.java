package br.com.dit.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dit.algafood.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
