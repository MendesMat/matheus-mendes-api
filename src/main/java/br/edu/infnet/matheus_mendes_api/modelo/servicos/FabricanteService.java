package br.edu.infnet.matheus_mendes_api.modelo.servicos;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;

@Service
public class FabricanteService implements CrudService<Fabricante, Integer> {

    private final Map<Integer, Fabricante> mapaFabricantes = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public Fabricante incluir(Fabricante fabricante) {
        Integer id = nextId.getAndIncrement();
        fabricante.setId(id);
        mapaFabricantes.put(id, fabricante);
        return fabricante;
    }

    @Override
    public Fabricante obterPorId(Integer id) {
        return mapaFabricantes.get(id);
    }

    @Override
    public Collection<Fabricante> obterLista() {
        return mapaFabricantes.values();
    }

    @Override
    public Fabricante atualizar(Integer id, Fabricante fabricante) {
        if(!mapaFabricantes.containsKey(id)) return null;
    	
    	fabricante.setId(id);
    	mapaFabricantes.put(id, fabricante);
        return fabricante;
    }

    @Override
    public boolean excluir(Integer id) {
        if (!mapaFabricantes.containsKey(id)) return false;
        
        mapaFabricantes.remove(id);
        return true;
    }
}