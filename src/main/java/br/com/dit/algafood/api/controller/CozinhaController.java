package br.com.dit.algafood.api.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.dit.algafood.api.dto.CozinhaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dit.algafood.api.model.CozinhaXmlWrapper;
import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.dit.algafood.domain.model.Cozinha;
import br.com.dit.algafood.domain.repositories.CozinhaRepository;
import br.com.dit.algafood.infrastructure.service.CozinhaServiceImpl;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	private CozinhaServiceImpl cozinhaService;

	public CozinhaController(CozinhaServiceImpl cozinhaService) {
		this.cozinhaService = cozinhaService;
	}

	@GetMapping
	public ResponseEntity<List<CozinhaDTO>> listar(){
		List<CozinhaDTO> cozinhasDTO = cozinhaService.listarTodos()
			.stream().map(CozinhaDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(cozinhasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CozinhaDTO> buscaPorId(@PathVariable Long id){
		Cozinha cozinha = cozinhaService.buscarPorId(id);
		return ResponseEntity.ok(new CozinhaDTO(cozinha));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CozinhaDTO> salvar(@RequestBody @Valid Cozinha cozinha) {
		Cozinha novaCozinha = cozinhaService.salvar(cozinha);
		return new ResponseEntity<>(new CozinhaDTO(cozinha), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CozinhaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha){
		Cozinha cozinhaPesquisada = cozinhaService.buscarPorId(id);
		BeanUtils.copyProperties(cozinha, cozinhaPesquisada, "id");
		Cozinha cozinhaAtualizada = cozinhaService.salvar(cozinhaPesquisada);
		return ResponseEntity.ok(new CozinhaDTO(cozinhaAtualizada)) ;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		cozinhaService.removerPorId(id);
		return ResponseEntity.noContent().build();
		
	}
}
