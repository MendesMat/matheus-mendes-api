package br.edu.infnet.matheus_mendes_api.interfaces;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

public interface CrudController<T, ID> {
	ResponseEntity<T> incluir(T entidade);
	ResponseEntity<T> buscaPorId(ID id);
	ResponseEntity<Collection<T>> buscaTodos();
	ResponseEntity<T> atualizar(ID id, T entidade);
	ResponseEntity<Void> deletar(ID id);
}