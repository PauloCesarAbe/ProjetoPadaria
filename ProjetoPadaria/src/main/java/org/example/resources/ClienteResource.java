package org.example.resources;

import org.example.entities.Cliente;
import org.example.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    public ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        List<Cliente> cliente = service.findALL();
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/id")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(service.findById(id));
        return cliente.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente){
        Cliente created = service.insert(cliente);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS.CREATED).body(created);
    }
    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Cliente cliente){
        if (service.update(id, cliente)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}