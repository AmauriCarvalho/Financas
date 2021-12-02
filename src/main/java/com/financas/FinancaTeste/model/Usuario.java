package com.financas.FinancaTeste.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends AbstractEntity implements UserDetails {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String login;

    @NotBlank
    @Length(min = 6, max = 30)
    private String senha;

    @Length(max = 50)
    private String nome;

    private Float saldo;

    @ManyToMany
    @JoinTable(name = "usuarios_roles", joinColumns =
    @JoinColumn(name = "usuario_id", referencedColumnName = "login"),
            inverseJoinColumns =
                    @JoinColumn(name = "role_id", referencedColumnName = "nome_role")
    )
    private List<Role> roles;

    @OneToMany
    private List<ListaProduto> listaProdutos;

    @OneToMany
    private List<ListaServico> listaServicos;

    @OneToMany
    private List<ListaConta> listaConta;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
