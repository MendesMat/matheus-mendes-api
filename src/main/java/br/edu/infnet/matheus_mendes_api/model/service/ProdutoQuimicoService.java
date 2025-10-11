package br.edu.infnet.matheus_mendes_api.model.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.model.domain.ProdutoQuimico;

@Service
public class ProdutoQuimicoService implements CrudService<ProdutoQuimico, Integer> {

	private final Map<Integer, ProdutoQuimico> mapaProdutosQuimicos = new ConcurrentHashMap<>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	@Override
	public ProdutoQuimico incluir(ProdutoQuimico produto) {
		Integer id = nextId.getAndIncrement();
		produto.setId(id);
		mapaProdutosQuimicos.put(produto.getId(), produto);
		return produto;
	}

	@Override
	public ProdutoQuimico obterPorId(Integer id) {
		return mapaProdutosQuimicos.get(id);
	}

	@Override
	public Collection<ProdutoQuimico> obterLista() {
		return mapaProdutosQuimicos.values();
	}

	@Override
	public ProdutoQuimico alterar(ProdutoQuimico produto, Integer id) {
		return mapaProdutosQuimicos.put(id, produto);
	}

	@Override
	public void excluir(Integer id) {
		mapaProdutosQuimicos.remove(id);
	}
}
