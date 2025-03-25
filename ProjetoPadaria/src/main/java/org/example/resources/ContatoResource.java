package org.example.resources;

import org.example.entities.Contato;
import org.example.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ContatoResource {
    @Autowired
    private ContatoService service;

    @GetMapping
    public List<Contato> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Contato findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Contato insert(@RequestBody Contato obj) {
        return service.insert(obj);
    }

    @PutMapping("/{id}")
    public Contato update(@PathVariable Long id, @RequestBody Contato obj) {
        return service.update(id, obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}