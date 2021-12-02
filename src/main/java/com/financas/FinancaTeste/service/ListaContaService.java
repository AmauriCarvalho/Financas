package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.ListaConta;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.repository.ListaContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaContaService implements ServiceInterface<ListaConta>{
    @Autowired
    private ListaContaRepository repository;

    public ListaContaService(){}

    @Override
    public ListaConta create(ListaConta listaConta){
        repository.save(listaConta);
        return listaConta;
    }


    @Override
    public List<ListaConta> findAll() {
        return repository.findAll();
    }

    @Override
    public ListaConta findById(Long id) {
        Optional<ListaConta> _listaConta = repository.findById(id);
        return _listaConta.orElse(null);
    }

    @Override
    public ListaConta update(ListaConta listaConta) {
        return repository.save(listaConta);
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
