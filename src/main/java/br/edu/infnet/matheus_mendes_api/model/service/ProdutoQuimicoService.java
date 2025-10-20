package br.edu.infnet.matheus_mendes_api.model.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.edu.infnet.matheus_mendes_api.controllers.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoFactory;

@Service
public class ProdutoQuimicoService implements CrudService<ProdutoQuimicoDto, Integer> {

    private final Map<Integer, ProdutoQuimicoBase> mapaProdutos = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public ProdutoQuimicoDto incluir(ProdutoQuimicoDto dto) {
        var novoProduto = ProdutoQuimicoFactory.criarProdutoPorTipo(
            dto.fabricanteId(),
            dto.tipoProduto(),
            dto.nomeComercial(),
            dto.registroAnvisa(),
            dto.validadeRegistro(),
            dto.formaFarmaceutica(),
            dto.principioAtivo(),
            dto.concentracao(),
            dto.diluente()
        );

        var id = nextId.getAndIncrement();
        novoProduto.setId(id);

        mapaProdutos.put(id, novoProduto);

        return ProdutoQuimicoDto.fromEntity(novoProduto);
    }

    @Override
    public ProdutoQuimicoDto obterPorId(Integer id) {
        var produto = mapaProdutos.get(id);
        return produto != null ? ProdutoQuimicoDto.fromEntity(produto) : null;
    }

    @Override
    public Collection<ProdutoQuimicoDto> obterLista() {
        return mapaProdutos.values().stream()
                .map(ProdutoQuimicoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoQuimicoDto atualizar(Integer id, ProdutoQuimicoDto dto) {
        if (!mapaProdutos.containsKey(id)) return null;

        var produtoAtualizado = ProdutoQuimicoFactory.criarProdutoPorTipo(
            dto.fabricanteId(),
            dto.tipoProduto(),
            dto.nomeComercial(),
            dto.registroAnvisa(),
            dto.validadeRegistro(),
            dto.formaFarmaceutica(),
            dto.principioAtivo(),
            dto.concentracao(),
            dto.diluente()
        );

        produtoAtualizado.setId(id);
        mapaProdutos.put(id, produtoAtualizado);

        return ProdutoQuimicoDto.fromEntity(produtoAtualizado);
    }

    public ProdutoQuimicoDto alterarAtivacao(Integer id) {
    	var produto = mapaProdutos.get(id);
        
        if (produto == null) return null;
        
        produto.setAtivo(!produto.getAtivo());
        mapaProdutos.put(id, produto);
        
        return ProdutoQuimicoDto.fromEntity(produto);
    }
    
    @Override
    public boolean excluir(Integer id) {
        return mapaProdutos.remove(id) != null;
    }
}