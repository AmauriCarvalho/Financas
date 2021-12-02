package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Categoria;
import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ServiceInterface<Categoria> {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaService(){}

    @Override
    public Categoria create(Categoria categoria){
        repository.save(categoria);
        return categoria;
    }


    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        Optional<Categoria> _categoria = repository.findById(id);
        return _categoria.orElse(null);
    }

    @Override
    public Categoria update(Categoria categoria) {
        return repository.save(categoria);
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
