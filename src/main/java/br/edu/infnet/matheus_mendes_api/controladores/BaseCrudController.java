package br.edu.infnet.matheus_mendes_api.controladores;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.infnet.matheus_mendes_api.interfaces.CrudService;

public abstract class BaseCrudController<T, ID> {

    protected final CrudService<T, ID> service;

    protected BaseCrudController(CrudService<T, ID> service) {
        this.service = service;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<T> incluir(@RequestBody T entidade) {
        T resultado = service.incluir(entidade);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> buscaPorId(@PathVariable ID id) {
        T resultado = service.obterPorId(id);
        return resultado != null ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<T>> buscaTodos() {
        return ResponseEntity.ok(service.obterLista());
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizar(@PathVariable ID id, @RequestBody T entidade) {
        T resultado = service.atualizar(id, entidade);
        return resultado != null ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable ID id) {
        boolean resultado = service.excluir(id);
        return resultado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}