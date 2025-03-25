package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    
    @Autowired
    public FornecedorRepository repository;

    public List<Fornecedor> findAll(){
        return repository.findAll();
    }

    public Optional<Fornecedor> findById(Long id){
        return repository.findById(id);
    }
    
    public Fornecedor insert(Fornecedor fornecedor){
        return repository.save(fornecedor);
    }

    public void delete(Long id ){
        repository.deleteById(id);
    }

    public boolean update(Long id, Fornecedor fornecedor){
        Optional<Fornecedor>optional=repository.findById(id);
        if (optional.isPresent()){
            Fornecedor fornecedor1 = optional.get();
            fornecedor1.setRazaoSocial(fornecedor.getRazaoSocial());
            fornecedor1.setNomeFantasia(fornecedor.getNomeFantasia());
            fornecedor1.setCnpj(fornecedor.getCnpj());
            fornecedor1.setStatus(fornecedor.getStatus());
            repository.save(fornecedor1);
            return true;
        }
        return false;
    }
}