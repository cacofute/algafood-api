package br.com.dit.algafood.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import br.com.dit.algafood.api.dto.CidadeDTO;
import br.com.dit.algafood.domain.model.Cidade;
import br.com.dit.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	private CidadeService cidadeService;

	public CidadeController(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}
	
	@GetMapping
	public ResponseEntity<List<CidadeDTO>> listarTodos(){
		List<CidadeDTO> listaDeCidadesDTO = new ArrayList<>();
		List<Cidade> listaDeCidades       = cidadeService.listarTodos();
		listaDeCidades.forEach( c -> listaDeCidadesDTO.add(new CidadeDTO(c)) );
		return ResponseEntity.ok(listaDeCidadesDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Cidade cidade = cidadeService.buscarPorId(id);
		return ResponseEntity.ok(new CidadeDTO(cidade));
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Cidade cidade){
		Cidade novaCidade = cidadeService.salvar(cidade);
		return new ResponseEntity<>(new CidadeDTO(novaCidade), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade){
		Cidade cidadePesquisada = cidadeService.buscarPorId(id);
		BeanUtils.copyProperties(cidade, cidadePesquisada, "id");
		Cidade cidadeAtualizada = cidadeService.atualizar(cidadePesquisada);
		return ResponseEntity.ok(new CidadeDTO(cidadeAtualizada));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		cidadeService.removerPorId(id);
		return ResponseEntity.noContent().build();

	}
	

}
