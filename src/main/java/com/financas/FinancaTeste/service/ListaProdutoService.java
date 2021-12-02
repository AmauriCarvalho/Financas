package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.ListaProduto;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.repository.ListaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaProdutoService implements ServiceInterface<ListaProduto>{

    @Autowired
    private ListaProdutoRepository repository;

    public ListaProdutoService(){}

    @Override
    public ListaProduto create(ListaProduto listaProduto){
        repository.save(listaProduto);
        return listaProduto;
    }


    @Override
    public List<ListaProduto> findAll() {
        return repository.findAll();
    }

    @Override
    public ListaProduto findById(Long id) {
        Optional<ListaProduto> _listaProduto = repository.findById(id);
        return _listaProduto.orElse(null);
    }

    @Override
    public ListaProduto update(ListaProduto listaProduto) {
        return repository.save(listaProduto);
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
