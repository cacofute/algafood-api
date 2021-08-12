package br.com.dit.algafood.api.dto;

import br.com.dit.algafood.domain.model.Cozinha;
import lombok.Value;

@Value
public class CozinhaDTO {

    Long id;
    String nome;

    public CozinhaDTO(Cozinha cozinha) {
        this.id   = cozinha.getId();
        this.nome = cozinha.getNome();
    }
}
