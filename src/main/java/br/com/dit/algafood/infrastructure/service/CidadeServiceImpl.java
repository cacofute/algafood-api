package br.com.dit.algafood.infrastructure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dit.algafood.domain.exception.CidadeNaoEncontradaException;
import br.com.dit.algafood.domain.exception.CidadeVazioException;
import br.com.dit.algafood.domain.exception.EstadoVazioException;
import br.com.dit.algafood.domain.exception.EstadoVazioNoBancoDeDadosException;
import br.com.dit.algafood.domain.exception.IdentificadorVazioCidadeException;
import br.com.dit.algafood.domain.exception.IdentificadorVazioEstadoException;
import br.com.dit.algafood.domain.model.Cidade;
import br.com.dit.algafood.domain.model.Estado;
import br.com.dit.algafood.domain.repositories.CidadeRepository;
import br.com.dit.algafood.domain.repositories.EstadoRepository;
import br.com.dit.algafood.domain.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{
	
	private final CidadeRepository cidadeRepository;
	private final EstadoRepository estadoRepository;

	public CidadeServiceImpl(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
	}

	@Override
	public List<Cidade> listarTodos() {
		return cidadeRepository.findAll();
	}

	@Override
	public Cidade salvar(Cidade cidade) {
		if(cidade == null) {
			throw new CidadeVazioException();
		}
		validarEstado(cidade.getEstado());
		Cidade novaCidade = cidadeRepository.save(cidade);;
		return novaCidade;
	}

	@Override
	public Cidade atualizar(Cidade cidade) {
		if(cidade == null) {
			throw new CidadeVazioException();
		}
		validarEstado(cidade.getEstado());
		return cidadeRepository.save(cidade);
	}

	@Override
	public void remover(Cidade cidade) {
		if(cidade == null) {
			throw new CidadeVazioException();
		}
		validarEstado(cidade.getEstado());
		buscarPorId(cidade.getId());
		cidadeRepository.delete(cidade);
		
	}

	@Override
	public void removerPorId(Long id) {
		buscarPorId(id);
		cidadeRepository.deleteById(id);
	}

	@Override
	public Cidade buscarPorId(Long id) {
		if(id == null) {
			throw new IdentificadorVazioCidadeException();
		}
		Cidade cidade = cidadeRepository.findById(id)
			.orElseThrow( () -> new CidadeNaoEncontradaException(id));
		return cidade;
	
	}
	
	/**
	 * 
	 * @param estado
	 */
	private void validarEstado(Estado estado) {
		if(estado == null) {
			throw new EstadoVazioException();
		}
		Long estadoId = estado.getId();
		if(estadoId == null) {
			throw new IdentificadorVazioEstadoException();
		}
		estadoRepository.findById(estadoId).orElseThrow(EstadoVazioNoBancoDeDadosException::new);

	}


}
