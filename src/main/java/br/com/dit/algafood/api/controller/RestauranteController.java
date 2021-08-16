package br.com.dit.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dit.algafood.api.dto.RestauranteDTO;
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
	public ResponseEntity<RestauranteDTO> salvar(@RequestBody @Valid Restaurante restaurante){
		Restaurante newRestaurante = restauranteService.salvar(restaurante);
		return new ResponseEntity<>(new RestauranteDTO(newRestaurante), HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RestauranteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante){
		Restaurante restauranteAtual = restauranteService.buscarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "dataCadastro");
		Restaurante newRestaurante = restauranteService.atualizar(restauranteAtual);				
		return new ResponseEntity<>(new RestauranteDTO(newRestaurante), HttpStatus.OK);
	
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<RestauranteDTO> atualizarParcial(@PathVariable Long id, 
			@RequestBody Map<String, Object> campos, HttpServletRequest request){
		Restaurante restaurante = restauranteService.buscarPorId(id);
		merge(campos, restaurante, request);
		return atualizar(id, restaurante);
	}
	
	/**
	 * 
	 * @param campos
	 * @param destino
	 */
	private void merge(Map<String, Object> campos, Restaurante destino, HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		try {			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurante origem  = mapper.convertValue(campos, Restaurante.class);
			
			campos.forEach( (prop, val) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, prop);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, origem);
				ReflectionUtils.setField(field, destino, novoValor);
				
			});
		}
		catch(IllegalArgumentException ex) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, servletServerHttpRequest);
		}
	}

}
