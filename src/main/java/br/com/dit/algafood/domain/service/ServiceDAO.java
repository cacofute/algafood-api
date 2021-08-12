package br.com.dit.algafood.domain.service;

import java.util.List;

public interface ServiceDAO <T, ID>{
	
	List<T> listarTodos();
	
	T salvar(T t);
	
	T atualizar(T t);
	
	void remover(T t);
	
	void removerPorId(ID id);
	
	T buscarPorId(ID id);

}
