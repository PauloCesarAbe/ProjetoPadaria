package org.example.services;

import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void reduzirEstoque(Long produtoId, Integer quantidade) {
        Produto produto = repository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        Integer estoqueAtual = produto.getProQuantidadeEstoque();
        if (estoqueAtual == null) {
            throw new IllegalStateException("Estoque do produto está nulo (produtoId = " + produtoId + ")");
        }

        if (quantidade > estoqueAtual) {
            throw new IllegalArgumentException("Estoque insuficiente para o produtoId = " + produtoId);
        }

        produto.setProQuantidadeEstoque(estoqueAtual - quantidade);
        repository.save(produto);
    }

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Produto insert(Produto produto) {
        return repository.save(produto);
    }

    public boolean update(Long id, Produto produto) {
        Optional<Produto> optionalProduto = repository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produtoSistema = optionalProduto.get();
            produtoSistema.setProNome(produto.getProNome());
            produtoSistema.setProPrecoCusto(produto.getProPrecoCusto());
            produtoSistema.setProPrecoVenda(produto.getProPrecoVenda());
            produtoSistema.setProDescricao(produto.getProDescricao());
            produtoSistema.setProCodigoBarras(produto.getProCodigoBarras());
            produtoSistema.setProAtivo(produto.getProAtivo());
            produtoSistema.setProMarca(produto.getProMarca());
            produtoSistema.setProDataAtualizacao(produto.getProDataAtualizacao());
            produtoSistema.setProDataCadastro(produto.getProDataCadastro());
            produtoSistema.setProCategoria(produto.getProCategoria());
            repository.save(produtoSistema);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}