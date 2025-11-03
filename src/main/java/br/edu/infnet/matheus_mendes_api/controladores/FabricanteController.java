package br.edu.infnet.matheus_mendes_api.controladores;

import org.springframework.web.bind.annotation.*;

import br.edu.infnet.matheus_mendes_api.controladores.dto.FabricanteComProdutosDto;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.servicos.ServicoFabricante;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController extends BaseCrudController<Fabricante, Integer> {

    private final ServicoFabricante servico;

    public FabricanteController(ServicoFabricante servico) {
        super(servico);
        this.servico = servico;
    }

    @GetMapping("/{id}/produtos")
    public FabricanteComProdutosDto obterFabricanteComProdutos(@PathVariable Integer id) {
        return servico.obterComProdutos(id);
    }
}
