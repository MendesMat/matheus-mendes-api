package br.edu.infnet.matheus_mendes_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.matheus_mendes_api.controllers.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoQuimicoController extends BaseCrudController<ProdutoQuimicoDto, Integer> {

    public ProdutoQuimicoController(CrudService<ProdutoQuimicoDto, Integer> service) {
        super(service);
    }
}