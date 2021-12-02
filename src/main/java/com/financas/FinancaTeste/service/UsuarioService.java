package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Produto;
import com.financas.FinancaTeste.model.Usuario;
import com.financas.FinancaTeste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements ServiceInterface<Usuario>{

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(){}

    @Override
    public Usuario create(Usuario usuario){
        repository.save(usuario);
        return usuario;
    }


    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> _usuario = repository.findById(id);
        return _usuario.orElse(null);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return repository.save(usuario);
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
