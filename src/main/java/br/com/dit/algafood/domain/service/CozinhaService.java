package br.com.dit.algafood.domain.service;

import br.com.dit.algafood.domain.model.Cozinha;

public interface CozinhaService extends ServiceDAO<Cozinha, Long>{

    boolean cozinhaValidaParaAtualizar(Cozinha cozinha);

    boolean cozinhaValidaParaSalvar(Cozinha cozinha);

}
