package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.model.Usuario;
import com.financas.FinancaTeste.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements ServiceInterface<Produto>{

    @Autowired
    private ProdutoRepository repository;

    public ProdutoService(){}

    @Override
    public Produto create(Produto produto){
        repository.save(produto);
        return produto;
    }


    @Override
    public List<Produto> findAll() {
        return repository.findAll();
    }


    @Override
    public Produto findById(Long id) {
        Optional<Produto> _produto = repository.findById(id);
        return _produto.orElse(null);
    }

    @Override
    public Produto update(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public boolean delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
