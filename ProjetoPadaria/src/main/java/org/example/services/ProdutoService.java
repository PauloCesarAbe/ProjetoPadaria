package org.example.services;

import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    public ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Optional<Produto> findById(Long id){
        return repository.findById(id);
    }

    public Produto insert(Produto produto){
        return repository.save(produto);
    }

    public void delete(Long id ){
        repository.deleteById(id);
    }

    public boolean update(Long id, Produto produto){
        Optional<Produto>optional=repository.findById(id);
        if (optional.isPresent()){
            Produto produto1 = optional.get();
            produto1.setDescricao(produto.getDescricao());
            produto1.setPrecoCusto(produto.getPrecoCusto());
            produto1.setPrecoVenda(produto.getPrecoVenda());
            produto1.setEstoque(produto.getEstoque());
            repository.save(produto1);
            return true;
        }
        return false;
    }
}