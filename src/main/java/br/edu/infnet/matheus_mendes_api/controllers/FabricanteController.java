package br.edu.infnet.matheus_mendes_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;
import br.edu.infnet.matheus_mendes_api.model.domain.Fabricante;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController extends BaseCrudController<Fabricante, Integer> {

    public FabricanteController(CrudService<Fabricante, Integer> service) {
        super(service);
    }
}