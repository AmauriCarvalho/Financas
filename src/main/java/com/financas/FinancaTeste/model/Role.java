package com.financas.FinancaTeste.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_role")
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    private String nome_role;

    @ManyToMany
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.nome_role;
    }
}
