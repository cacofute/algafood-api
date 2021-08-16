package br.com.dit.algafood.infrastructure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dit.algafood.domain.exception.IdentificadorVazioRestauranteException;
import br.com.dit.algafood.domain.exception.RestauranteNaoEncontradoException;
import br.com.dit.algafood.domain.exception.RestauranteVazioException;
import br.com.dit.algafood.domain.model.Restaurante;
import br.com.dit.algafood.domain.repositories.RestauranteRepository;
import br.com.dit.algafood.domain.service.CozinhaService;
import br.com.dit.algafood.domain.service.RestauranteService;

@Service
public class RestauranteServiceImpl implements RestauranteService{

	private RestauranteRepository restauranteRepository;
	private CozinhaService cozinhaService;

	public RestauranteServiceImpl(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
		this.restauranteRepository = restauranteRepository;
		this.cozinhaService = cozinhaService;
	}

	@Override
	public List<Restaurante> listarTodos() {
		return restauranteRepository.findAll();
	}

	@Override
	public Restaurante buscarPorId(Long id) {
		if(id == null){
			throw new IdentificadorVazioRestauranteException();
		}
		return restauranteRepository.findById(id)
			.orElseThrow(() -> new RestauranteNaoEncontradoException(id));
		
	}
	
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		if(restaurante == null){
			throw new RestauranteVazioException();
		}
		cozinhaService.cozinhaValidaParaSalvar(restaurante.getCozinha());
		return restauranteRepository.save(restaurante);
	}

	/**
	 * 
	 */
	public Restaurante atualizar(Restaurante restaurante) {
		if(restaurante == null){
			throw new RestauranteVazioException();
		}
		buscarPorId(restaurante.getId());
		cozinhaService.cozinhaValidaParaAtualizar(restaurante.getCozinha());
		return restauranteRepository.save(restaurante);
		
	}

	@Override
	public void remover(Restaurante restaurante) {
		if(restaurante == null){
			throw new RestauranteVazioException();
		}
		removerPorId(restaurante.getId());
	}

	@Override
	public void removerPorId(Long id) {
		buscarPorId(id);
		restauranteRepository.deleteById(id);
	}


}
