package br.com.dit.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.dit.algafood.api.dto.RestauranteDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.model.Restaurante;
import br.com.dit.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	private RestauranteService restauranteService;

	public RestauranteController(RestauranteService restauranteService) {
		this.restauranteService = restauranteService;
	}
	
	@GetMapping
	public ResponseEntity<List<RestauranteDTO>>ListarTodos(){
		List<RestauranteDTO> restauranteDTOS = restauranteService.listarTodos()
			.stream()
			.map(RestauranteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(restauranteDTOS);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteDTO> buscarPorId(@PathVariable Long id){
		Restaurante restaurante = restauranteService.buscarPorId(id);
		return ResponseEntity.ok(new RestauranteDTO(restaurante));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteDTO> salvar(@RequestBody Restaurante restaurante){
		Restaurante newRestaurante = restauranteService.salvar(restaurante);
		return new ResponseEntity<>(new RestauranteDTO(newRestaurante), HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RestauranteDTO> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		Restaurante restauranteAtual = restauranteService.buscarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "dataCadastro");
		Restaurante newRestaurante = restauranteService.atualizar(restauranteAtual);
		return new ResponseEntity<>(new RestauranteDTO(newRestaurante), HttpStatus.CREATED);

		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		Restaurante restaurante = restauranteService.buscarPorId(id);
		merge(campos, restaurante);
		return atualizar(id, restaurante);
	}
	
	/**
	 * 
	 * @param campos
	 * @param destino
	 */
	private void merge(Map<String, Object> campos, Restaurante destino) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurante origem  = mapper.convertValue(campos, Restaurante.class);
		
		campos.forEach( (prop, val) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, prop);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, origem);
			ReflectionUtils.setField(field, destino, novoValor);
			
		});
	}

}
