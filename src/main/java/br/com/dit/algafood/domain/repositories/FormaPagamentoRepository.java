package br.com.dit.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dit.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	

}
