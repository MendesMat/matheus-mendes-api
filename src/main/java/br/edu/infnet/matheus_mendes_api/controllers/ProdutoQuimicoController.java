package br.edu.infnet.matheus_mendes_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoBase;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoQuimicoController extends BaseCrudController<ProdutoQuimicoBase, Integer> {

    public ProdutoQuimicoController(CrudService<ProdutoQuimicoBase, Integer> service) {
        super(service);
    }
}