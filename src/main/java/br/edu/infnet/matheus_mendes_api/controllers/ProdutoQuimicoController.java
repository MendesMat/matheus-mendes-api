package br.edu.infnet.matheus_mendes_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.matheus_mendes_api.controllers.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.model.service.ProdutoQuimicoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoQuimicoController extends BaseCrudController<ProdutoQuimicoDto, Integer> {

	private final ProdutoQuimicoService service;
	
    public ProdutoQuimicoController(ProdutoQuimicoService service) {
        super(service);
        this.service = service;
    }
    
    @PatchMapping({"id"})
    public ResponseEntity<ProdutoQuimicoDto> alterarAtivacao(@PathVariable Integer id) {
    	var resultado = service.alterarAtivacao(id);
    	return resultado != null ? 
    			ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }
}