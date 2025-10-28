package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.matheus_mendes_api.controladores.dto.*;
import br.edu.infnet.matheus_mendes_api.excecoes.*;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoFactory;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.validacoes.validarProdutoQuimico;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioProdutoQuimico;

@Service
@Transactional
public class ServicoProdutoQuimico implements CrudService<ProdutoQuimicoDto, Integer> {

    private final RepositorioProdutoQuimico repositorio;

    public ServicoProdutoQuimico(RepositorioProdutoQuimico repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ProdutoQuimicoDto incluir(ProdutoQuimicoDto dto) {
        if (dto == null) 
            throw new ExcecaoRecursoInvalido("Os dados do produto químico não podem ser nulos.");

        validarProdutoQuimico.validarDto(dto);

        if (repositorio.existsByRegistroAnvisa(dto.registroAnvisa()))
            throw new ExcecaoRecursoDuplicado("Já existe um produto com registro ANVISA: " + dto.registroAnvisa());

        ProdutoQuimicoBase produto = ProdutoQuimicoFactory.criarProduto(dto);
        ProdutoQuimicoBase salvo = repositorio.save(produto);
        return MapeadorProdutoQuimico.aPartirDeEntidade(salvo);
    }

    @Override
    public ProdutoQuimicoDto obterPorId(Integer id) {
        ProdutoQuimicoBase produto = repositorio.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado."));
        return MapeadorProdutoQuimico.aPartirDeEntidade(produto);
    }

    @Override
    public Collection<ProdutoQuimicoDto> obterLista() {
        return repositorio.findAll().stream()
                .map(MapeadorProdutoQuimico::aPartirDeEntidade)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoQuimicoDto atualizar(Integer id, ProdutoQuimicoDto dto) {
        if (!repositorio.existsById(id))
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para atualização.");

        validarProdutoQuimico.validarDto(dto);

        ProdutoQuimicoBase produtoAtualizado = ProdutoQuimicoFactory.criarProduto(dto);
        produtoAtualizado.setId(id);
        ProdutoQuimicoBase salvo = repositorio.save(produtoAtualizado);

        return MapeadorProdutoQuimico.aPartirDeEntidade(salvo);
    }

    @Override
    public boolean excluir(Integer id) {
        if (!repositorio.existsById(id))
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para exclusão.");
        repositorio.deleteById(id);
        return true;
    }

    public ProdutoQuimicoDto alterarAtivacao(Integer id) {
        ProdutoQuimicoBase produto = repositorio.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para alteração de ativação."));

        produto.setAtivo(!produto.getAtivo());
        ProdutoQuimicoBase salvo = repositorio.save(produto);

        return MapeadorProdutoQuimico.aPartirDeEntidade(salvo);
    }
}