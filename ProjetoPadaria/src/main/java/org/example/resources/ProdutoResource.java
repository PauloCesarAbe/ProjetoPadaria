package org.example.resources;

import org.example.entities.Produto;
import org.example.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {
    
    @Autowired
    public ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        List<Produto> produto = service.findAll();
        return ResponseEntity.ok(produto);
    }
    @GetMapping("/id")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> produto = service.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto){
        Produto created = service.insert(produto);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS.CREATED).body(created);
    }
    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Produto produto){
        if (service.update(id, produto)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}