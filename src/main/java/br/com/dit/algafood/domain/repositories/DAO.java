package br.com.dit.algafood.domain.repositories;

import java.util.List;

public interface DAO <T, ID>{
	
	List<T> listarTodos();
	
	T salvar(T t);
	
	T atualizar(T t);
	
	void remover(T t);
	
	void removerPorId(ID id);
	
	T buscarPorId(ID id);

}
