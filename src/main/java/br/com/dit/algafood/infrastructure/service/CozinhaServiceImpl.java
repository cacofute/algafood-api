package br.com.dit.algafood.infrastructure.service;

import br.com.dit.algafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.dit.algafood.domain.exception.CozinhaVaziaException;
import br.com.dit.algafood.domain.exception.IdentificadorVazioCozinhaException;
import br.com.dit.algafood.domain.service.CozinhaService;
import org.springframework.stereotype.Service;

import br.com.dit.algafood.domain.model.Cozinha;
import br.com.dit.algafood.domain.repositories.CozinhaRepository;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	private CozinhaRepository cozinhaRepository;

	public CozinhaServiceImpl(CozinhaRepository cozinhaRepository) {
		this.cozinhaRepository = cozinhaRepository;
	}

	@Override
	public List<Cozinha> listarTodos() {
		return cozinhaRepository.findAll();
	}

	/**
	 * 
	 * @param cozinha
	 * @return
	 */
	public Cozinha salvar(Cozinha cozinha) {
		if(cozinha == null){
			throw new CozinhaVaziaException();
		}
		return cozinhaRepository.save(cozinha);
	}

	@Override
	public Cozinha atualizar(Cozinha cozinha) {
		if(cozinha == null){
			throw new CozinhaVaziaException();
		}
		buscarPorId(cozinha.getId());
		return cozinhaRepository.save(cozinha);
	}

	@Override
	public void remover(Cozinha cozinha) {
		if(cozinha == null){
			throw new CozinhaVaziaException();
		}
		removerPorId(cozinha.getId());
	}

	@Override
	public void removerPorId(Long id) {
		Cozinha cozinha = buscarPorId(id);
		cozinhaRepository.delete(cozinha);
	}

	@Override
	public Cozinha buscarPorId(Long id) {
		if(id == null){
			throw new IdentificadorVazioCozinhaException();
		}
		return cozinhaRepository.findById(id).orElseThrow( () -> new CozinhaNaoEncontradaException(id));
	}

	@Override
	public boolean cozinhaValidaParaAtualizar(Cozinha cozinha) {
		if(cozinha == null) {
			throw new CozinhaVaziaException();
		}
		Long cozinhaId = cozinha.getId();
		if(cozinhaId == null) {
			throw new IdentificadorVazioCozinhaException();
		}
		cozinhaRepository.findById(cozinhaId).orElseThrow(()-> new CozinhaNaoEncontradaException(cozinhaId));
		return true;
	}

	@Override
	public boolean cozinhaValidaParaSalvar(Cozinha cozinha) {
		if(cozinha == null) {
			throw new CozinhaVaziaException();
		}
		return true;
	}
}
