package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.matheus_mendes_api.controladores.dto.*;
import br.edu.infnet.matheus_mendes_api.excecoes.*;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoFactory;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.validacoes.validarProdutoQuimico;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioFabricante;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioProdutoQuimico;

@Service
@Transactional
public class ServicoProdutoQuimico implements CrudService<ProdutoQuimicoDto, Integer> {

    private final RepositorioProdutoQuimico repositorio;
    private final RepositorioFabricante repositorioFabricante;

    public ServicoProdutoQuimico(RepositorioProdutoQuimico repositorio, RepositorioFabricante repositorioFabricante) {
        this.repositorio = repositorio;
        this.repositorioFabricante = repositorioFabricante;
    }

    @Override
    public ProdutoQuimicoDto incluir(ProdutoQuimicoDto dto) {
        if (dto == null)
            throw new ExcecaoRecursoInvalido("Os dados do produto químico não podem ser nulos.");

        validarProdutoQuimico.validarDto(dto);

        if (repositorio.existsByRegistroAnvisa(dto.registroAnvisa()))
            throw new ExcecaoRecursoDuplicado("Já existe um produto com registro ANVISA: " + dto.registroAnvisa());

        var fabricante = repositorioFabricante.findById(dto.fabricante().id())
                .orElseThrow(() -> new ExcecaoRecursoInvalido("Fabricante precisa existir no banco."));

        ProdutoQuimicoBase produto = ProdutoQuimicoFactory.criarProduto(dto, fabricante);
        ProdutoQuimicoBase salvo = repositorio.save(produto);

        return MapeadorProdutoQuimico.aPartirDeEntidade(salvo);
    }

    // Chamado apenas pelo loader
    public ProdutoQuimicoBase incluirEntidade(ProdutoQuimicoDto dto) {
        validarProdutoQuimico.validarDto(dto);

        if (repositorio.existsByRegistroAnvisa(dto.registroAnvisa()))
            throw new ExcecaoRecursoDuplicado("Já existe um produto com registro ANVISA: " + dto.registroAnvisa());

        Fabricante fabricante = repositorioFabricante.findById(dto.fabricante().id())
                .orElseThrow(() -> new ExcecaoRecursoInvalido("Fabricante precisa existir no banco."));

        ProdutoQuimicoBase produto = ProdutoQuimicoFactory.criarProduto(dto, fabricante);
        return repositorio.save(produto);
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

        Fabricante fabricante = repositorioFabricante.findById(dto.fabricante().id())
                .orElseThrow(() -> new ExcecaoRecursoInvalido("Fabricante precisa existir no banco."));

        ProdutoQuimicoBase produtoAtualizado = ProdutoQuimicoFactory.criarProduto(dto, fabricante);
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

    @Transactional(readOnly = true)
    public Collection<ProdutoQuimicoDto> buscarPorNome(String nome) {
        return repositorio.findByNomeComercialContainingIgnoreCase(nome)
                .stream()
                .map(MapeadorProdutoQuimico::aPartirDeEntidade)
                .toList();
    }

    @Transactional(readOnly = true)
    public Collection<ProdutoQuimicoDto> buscarPorConcentracao(double minimo, double maximo) {
        return repositorio.findByConcentracaoBetween(minimo, maximo)
                .stream()
                .map(MapeadorProdutoQuimico::aPartirDeEntidade)
                .toList();
    }
}