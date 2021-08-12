package br.com.dit.algafood.api.dto;

import br.com.dit.algafood.domain.model.Restaurante;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestauranteDTO {

    Long id;
    String nome;
    CozinhaDTO cozinha;
    BigDecimal taxaFrete;

    public RestauranteDTO(Restaurante restaurante) {
        id        = restaurante.getId();
        nome      = restaurante.getNome();
        cozinha   = new CozinhaDTO(restaurante.getCozinha());
        taxaFrete = restaurante.getTaxaFrete();

    }
}
