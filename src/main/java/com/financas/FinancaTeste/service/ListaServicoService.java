package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.ListaServico;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.repository.ListaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaServicoService implements ServiceInterface<ListaServico>{
    @Autowired
    private ListaServicoRepository repository;

    public ListaServicoService(){}

    @Override
    public ListaServico create(ListaServico listaServico){
        repository.save(listaServico);
        return listaServico;
    }


    @Override
    public List<ListaServico> findAll() {
        return repository.findAll();
    }

    @Override
    public ListaServico findById(Long id) {
        Optional<ListaServico> _listaServico = repository.findById(id);
        return _listaServico.orElse(null);
    }

    @Override
    public ListaServico update(ListaServico listaServico) {
        return repository.save(listaServico);
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
