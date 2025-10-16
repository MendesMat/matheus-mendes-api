package br.edu.infnet.matheus_mendes_api.model.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.model.domain.ProdutoQuimicoBase;

@Service
public class ProdutoQuimicoService implements CrudService<ProdutoQuimicoBase, Integer> {

    private final Map<Integer, ProdutoQuimicoBase> mapaProdutos = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public ProdutoQuimicoBase incluir(ProdutoQuimicoBase produto) {
        Integer id = nextId.getAndIncrement();
        produto.setId(id);
        mapaProdutos.put(id, produto);
        return produto;
    }

    @Override
    public ProdutoQuimicoBase obterPorId(Integer id) {
        return mapaProdutos.get(id);
    }

    @Override
    public Collection<ProdutoQuimicoBase> obterLista() {
        return mapaProdutos.values();
    }

    @Override
    public ProdutoQuimicoBase atualizar(Integer id, ProdutoQuimicoBase produto) {
        if (!mapaProdutos.containsKey(id)) return null;

        produto.setId(id);
        mapaProdutos.put(id, produto);
        return produto;
    }

    @Override
    public boolean excluir(Integer id) {
        if (!mapaProdutos.containsKey(id)) return false;

        mapaProdutos.remove(id);
        return true;
    }
}