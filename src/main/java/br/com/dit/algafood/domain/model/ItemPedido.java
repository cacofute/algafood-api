package br.com.dit.algafood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = -1775058619793764620L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
	@OneToOne
	private Produto produto;

}
