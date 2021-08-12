package br.com.dit.algafood.api.dto;

import br.com.dit.algafood.domain.model.Estado;
import lombok.Value;

@Value
public class EstadoDTO {
	Long id;
	String nome;
	public EstadoDTO(Estado estado) {
		this.id   = estado.getId();
		this.nome = estado.getNome();
	}
	
	
}
