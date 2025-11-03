package br.edu.infnet.matheus_mendes_api.controladores;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.modelo.servicos.ServicoProdutoQuimico;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoQuimicoController extends BaseCrudController<ProdutoQuimicoDto, Integer> {

	private final ServicoProdutoQuimico service;
	
    public ProdutoQuimicoController(ServicoProdutoQuimico service) {
        super(service);
        this.service = service;
    }
    
    @PatchMapping({"/{id}/ativacao"})
    public ResponseEntity<ProdutoQuimicoDto> alternarAtivacao(@PathVariable Integer id) {
    	var resultado = service.alterarAtivacao(id);
    	return resultado != null ? 
    			ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/buscar/nome")
    public ResponseEntity<Collection<ProdutoQuimicoDto>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @GetMapping("/buscar/concentracao")
    public ResponseEntity<Collection<ProdutoQuimicoDto>> buscarPorConcentracao(
            @RequestParam double minimo, @RequestParam double maximo) {
        return ResponseEntity.ok(service.buscarPorConcentracao(minimo, maximo));
    }
}