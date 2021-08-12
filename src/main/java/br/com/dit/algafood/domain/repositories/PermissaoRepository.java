package br.com.dit.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dit.algafood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
