package br.com.dit.algafood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = -5219052495264495480L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Usuario cliente;
	
	@ManyToOne
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	private Restaurante restaurante;
	
	@Embedded
	private Endereco endereco;
	
	

}
