package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoInvalido;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoNaoEncontrado;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoFactory;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.validacoes.validarProdutoQuimico;

@Service
public class ProdutoQuimicoService implements CrudService<ProdutoQuimicoDto, Integer> {

    private final Map<Integer, ProdutoQuimicoBase> mapaProdutos = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public ProdutoQuimicoDto incluir(ProdutoQuimicoDto dto) {
    	
    	if(dto == null) {
    		throw new ExcecaoRecursoInvalido("Os dados do produto químico não podem ser nulos.");
    	}
    	
    	validarProdutoQuimico.validarDto(dto);
    	
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
        
        if (produto == null) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado.");
        }
        return ProdutoQuimicoDto.fromEntity(produto);
    }

    @Override
    public Collection<ProdutoQuimicoDto> obterLista() {
        return mapaProdutos.values().stream()
                .map(ProdutoQuimicoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoQuimicoDto atualizar(Integer id, ProdutoQuimicoDto dto) {
        if (!mapaProdutos.containsKey(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para atualização.");
        }
        
        validarProdutoQuimico.validarDto(dto);

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
        
        if (produto == null) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para alteração de ativação.");
        }

        produto.setAtivo(!produto.getAtivo());
        mapaProdutos.put(id, produto);

        return ProdutoQuimicoDto.fromEntity(produto);
    }
    
    @Override
    public boolean excluir(Integer id) {
        if (!mapaProdutos.containsKey(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para exclusão.");
        }
        
        mapaProdutos.remove(id);
        return true;
    }
}