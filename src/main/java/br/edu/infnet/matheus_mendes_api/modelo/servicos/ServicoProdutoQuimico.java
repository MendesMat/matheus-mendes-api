package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoDuplicado;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoInvalido;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoNaoEncontrado;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoFactory;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.validacoes.validarProdutoQuimico;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioProdutoQuimico;

@Service
@Transactional
public class ServicoProdutoQuimico implements CrudService<ProdutoQuimicoDto, Integer> {

    private final RepositorioProdutoQuimico repositorio;

    public ServicoProdutoQuimico(RepositorioProdutoQuimico produtoQuimicoRepository) {
        this.repositorio = produtoQuimicoRepository;
    }

    @Override
    public ProdutoQuimicoDto incluir(ProdutoQuimicoDto dto) {
        if (dto == null) { throw new ExcecaoRecursoInvalido("Os dados do produto químico não podem ser nulos."); }
        
        validarProdutoQuimico.validarDto(dto);
        
        if (repositorio.existsByRegistroAnvisa(dto.registroAnvisa())) {
            throw new ExcecaoRecursoDuplicado("Já existe um produto com registro ANVISA: " + dto.registroAnvisa());
        }

        var novoProduto = ProdutoQuimicoFactory.criarProdutoPorTipo(
                dto.fabricante(),
                dto.tipoProduto(),
                dto.nomeComercial(),
                dto.registroAnvisa(),
                dto.validadeRegistro(),
                dto.formaFarmaceutica(),
                dto.principioAtivo(),
                dto.concentracao(),
                dto.diluente()
        );

        var entidadeSalva = repositorio.save(novoProduto);
        return ProdutoQuimicoDto.fromEntity(entidadeSalva);
    }

    @Override
    public ProdutoQuimicoDto obterPorId(Integer id) {
        var produto = repositorio.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado."));

        return ProdutoQuimicoDto.fromEntity(produto);
    }

    @Override
    public Collection<ProdutoQuimicoDto> obterLista() {
        return repositorio.findAll().stream()
                .map(ProdutoQuimicoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoQuimicoDto atualizar(Integer id, ProdutoQuimicoDto dto) {
        if (!repositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para atualização.");
        }

        validarProdutoQuimico.validarDto(dto);

        var produtoAtualizado = ProdutoQuimicoFactory.criarProdutoPorTipo(
                dto.fabricante(),
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
        var entidadeSalva = repositorio.save(produtoAtualizado);

        return ProdutoQuimicoDto.fromEntity(entidadeSalva);
    }

    public ProdutoQuimicoDto alterarAtivacao(Integer id) {
        var produto = repositorio.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para alteração de ativação."));

        produto.setAtivo(!produto.getAtivo());
        var entidadeSalva = repositorio.save(produto);

        return ProdutoQuimicoDto.fromEntity(entidadeSalva);
    }

    @Override
    public boolean excluir(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Produto químico com ID " + id + " não encontrado para exclusão.");
        }

        repositorio.deleteById(id);
        return true;
    }
}
