package br.com.dit.algafood.infrastructure.service;

import java.util.List;

import br.com.dit.algafood.domain.exception.IdentificadorVazioEstadoException;
import br.com.dit.algafood.domain.exception.EstadoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.dit.algafood.domain.exception.EntidadeEmUsoException;
import br.com.dit.algafood.domain.model.Estado;
import br.com.dit.algafood.domain.repositories.EstadoRepository;
import br.com.dit.algafood.domain.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService{
	
	private EstadoRepository estadoRepository;

	public EstadoServiceImpl(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	@Override
	public List<Estado> listarTodos() {
		return estadoRepository.findAll();
	}

	@Override
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public Estado atualizar(Estado estado) {
		return estadoRepository.save(estado);
		
	}

	@Override
	public void remover(Estado estado) {
		buscarPorId(estado.getId());
		try {
			estadoRepository.delete(estado);
		}
		catch(DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException("Estado", estado.getId());
		}
	}

	@Override
	public void removerPorId(Long id) {
		Estado estado = new Estado();
		estado.setId(id);
		remover(estado);
	}

	@Override
	public Estado buscarPorId(Long id) {
		if(id == null){
			throw new IdentificadorVazioEstadoException();
		}
		Estado estado = estadoRepository
			.findById(id)
			.orElseThrow(() -> new EstadoNaoEncontradoException(id));
		return estado;
		
	}

}
