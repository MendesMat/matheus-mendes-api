package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.repositorios.RepositorioFabricante;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoNaoEncontrado;

@Service
@Transactional
public class ServicoFabricante implements CrudService<Fabricante, Integer> {

    private final RepositorioFabricante respositorio;

    public ServicoFabricante(RepositorioFabricante repositorio) {
        this.respositorio = repositorio;
    }

    @Override
    public Fabricante incluir(Fabricante fabricante) {
        return respositorio.save(fabricante);
    }

    @Override
    public Fabricante obterPorId(Integer id) {
        return respositorio.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado."));
    }

    @Override
    public Collection<Fabricante> obterLista() {
        return respositorio.findAll();
    }

    @Override
    public Fabricante atualizar(Integer id, Fabricante fabricante) {
        if (!respositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado para atualização.");
        }

        fabricante.setId(id);
        return respositorio.save(fabricante);
    }

    @Override
    public boolean excluir(Integer id) {
        if (!respositorio.existsById(id)) {
            throw new ExcecaoRecursoNaoEncontrado("Fabricante com ID " + id + " não encontrado para exclusão.");
        }

        respositorio.deleteById(id);
        return true;
    }
}
