package org.example.services;

import org.example.entities.Contato;
import org.example.repositories.ContatoRepository;
import org.example.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository repository;

    @Autowired
    private ClienteService clienteService;

    public List<Contato> getAll() {

        return repository.findAll();
    }

    public Page<Contato> getAllPage(Pageable pageable) {

        return repository.findAll(pageable);
    }
    public Contato findById(Long id) {
        Optional<Contato> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Contato.class.getName()));
    }

    public Contato insert(Contato obj) {
        obj.setId(null);
        obj.setCliente(clienteService.findById(obj.getCliente().getId()));
        obj = repository.save(obj);
        return obj;
    }

    @SuppressWarnings("deprecation")
    public Contato update(Long id, Contato obj) {
        Contato entity = repository.getOne(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Contato entity, Contato obj) {
        entity.setCliente(obj.getCliente());
        entity.setCelular(obj.getCelular());
        entity.setEmail(obj.getEmail());
        entity.setTelefone(obj.getTelefone());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}