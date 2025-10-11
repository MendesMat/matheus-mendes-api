package br.edu.infnet.matheus_mendes_api.interfaces;

import java.util.Collection;

public interface CrudService<T, ID> {
	T incluir(T entidade);
	T obterPorId(ID id);
	Collection<T> obterLista();
	T alterar(T entidade, ID id);
	void excluir(ID id);
}
