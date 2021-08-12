package br.com.dit.algafood.api.dto;

import br.com.dit.algafood.domain.model.Cidade;
import lombok.Value;

@Value
public class CidadeDTO {
	Long id;
	String nome;
	EstadoDTO estado;
	public CidadeDTO(Cidade cidade) {
		this.id     = cidade.getId();
		this.nome   = cidade.getNome();
		this.estado = new EstadoDTO(cidade.getEstado());
	}

}
