package org.example.resources;

import org.example.entities.Contato;
import org.example.entities.FormaPagamento;
import org.example.services.ContatoService;
import org.example.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/formaPagamento")
public class FormaPagamentoResource {

    @Autowired
    public FormaPagamentoService service;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> getAll(){
        List<FormaPagamento> formaPagamento = service.findAll();
        return ResponseEntity.ok(formaPagamento);
    }
    @GetMapping("/id")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long id) {
        Optional<FormaPagamento> formaPagamento = service.findById(id);
        return formaPagamento.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<FormaPagamento> insert(@RequestBody FormaPagamento formaPagamento){
        FormaPagamento created = service.insert(formaPagamento);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS.CREATED).body(created);
    }
    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody FormaPagamento formaPagamento){
        if (service.update(id, formaPagamento)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}