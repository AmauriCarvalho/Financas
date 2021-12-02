package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Conta;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService implements ServiceInterface<Conta>{
    @Autowired
    private ContaRepository repository;

    public ContaService(){}

    @Override
    public Conta create(Conta conta){
        repository.save(conta);
        return conta;
    }


    @Override
    public List<Conta> findAll() {
        return repository.findAll();
    }

    @Override
    public Conta findById(Long id) {
        Optional<Conta> _conta = repository.findById(id);
        return _conta.orElse(null);
    }

    @Override
    public Conta update(Conta conta) {
        return repository.save(conta);
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
