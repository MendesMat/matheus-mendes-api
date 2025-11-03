package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.matheus_mendes_api.controladores.dto.FabricanteComProdutosDto;
import br.edu.infnet.matheus_mendes_api.controladores.dto.FabricanteDto;
import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioFabricante;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoDuplicado;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoNaoEncontrado;

@Service
@Transactional
public class ServicoFabricante implements CrudService<Fabricante, Integer> {

    private final RepositorioFabricante repositorio;

    public ServicoFabricante(RepositorioFabricante repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Fabricante incluir(Fabricante fabricante) {
        if (repositorio.existsByCnpj(fabricante.getCnpj())) {
            throw new ExcecaoRecursoDuplicado("Já existe um fabricante com CNPJ: " + fabricante.getCnpj());
        }
        return repositorio.save(fabricante);
    }

    @Override
    public Fabricante obterPorId(Integer id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado."));
    }

    @Override
    public Collection<Fabricante> obterLista() {
        return repositorio.findAll();
    }

    @Override
    public Fabricante atualizar(Integer id, Fabricante fabricante) {
        if (!repositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado para atualização.");
        }
        fabricante.setId(id);
        return repositorio.save(fabricante);
    }

    @Override
    public boolean excluir(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado para exclusão.");
        }
        repositorio.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public FabricanteComProdutosDto obterComProdutos(Integer id) {
        Fabricante fabricante = repositorio.findByIdComProdutos(id)
            .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado."));

        var produtosDto = fabricante.getProdutos().stream()
            .map(p -> new ProdutoQuimicoDto(
                p.getId(),
                new FabricanteDto(fabricante.getId(), fabricante.getNome(), fabricante.getCnpj()), // evita loop
                p.getTipoProduto(),
                p.getNomeComercial(),
                p.getRegistroAnvisa(),
                p.getValidadeRegistroAnvisa(),
                p.getAtivo(),
                p.getFormaFarmaceutica(),
                p.getPrincipioAtivo(),
                p.getConcentracao(),
                p.getDiluente(),
                null,
                null,
                null
            ))
            .collect(Collectors.toList());

        return new FabricanteComProdutosDto(fabricante.getId(), fabricante.getNome(), fabricante.getCnpj(), produtosDto);
    }
}
