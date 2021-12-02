package com.financas.FinancaTeste.service;

import com.financas.FinancaTeste.model.Usuario;
import com.financas.FinancaTeste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(),
                true, true, true, true,
                usuario.getAuthorities());
    }
}
