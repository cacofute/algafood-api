package br.com.dit.algafood.api.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.dit.algafood.domain.exception.EstadoNaoEncontradoException;
import br.com.dit.algafood.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dit.algafood.api.dto.EstadoDTO;
import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.model.Estado;
import br.com.dit.algafood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	private EstadoService estadoService;
	
	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> listarTodos(){
		List<EstadoDTO> listaEstadosDTO = new ArrayList<>();
		estadoService.listarTodos().forEach( e -> listaEstadosDTO.add(new EstadoDTO(e)));
		return ResponseEntity.ok(listaEstadosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Estado estado = estadoService.buscarPorId(id);
		return ResponseEntity.ok(new EstadoDTO(estado));
	}
	
	@PostMapping
	public ResponseEntity<EstadoDTO> salvar(@RequestBody Estado estado){
		Estado novoEstado = estadoService.salvar(estado);
		return new ResponseEntity<>(new EstadoDTO(novoEstado), HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado){
		Estado estadoPesquisada = estadoService.buscarPorId(id);
		BeanUtils.copyProperties(estado, estadoPesquisada, "id");
		Estado novoEstado = estadoService.atualizar(estadoPesquisada);
		return ResponseEntity.ok(new EstadoDTO(novoEstado));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		estadoService.removerPorId(id);
		return ResponseEntity.noContent().build();

	}
}
