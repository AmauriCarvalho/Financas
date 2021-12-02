package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.model.Servico;
import com.financas.FinancaTeste.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService implements ServiceInterface<Servico>{

    @Autowired
    private ServicoRepository repository;

    public ServicoService(){}

    @Override
    public Servico create(Servico servico){
        repository.save(servico);
        return servico;
    }


    @Override
    public List<Servico> findAll() {
        return repository.findAll();
    }

    @Override
    public Servico findById(Long id) {
        Optional<Servico> _servico = repository.findById(id);
        return _servico.orElse(null);
    }

    @Override
    public Servico update(Servico servico) {
        return repository.save(servico);
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
