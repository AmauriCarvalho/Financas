package com.financas.FinancaTeste.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_lista_servico")
@Getter
@Setter
@NoArgsConstructor
public class ListaServico extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 1, max = 30)
    private String nome_listaServ;

    @ManyToMany
    private List<Servico> servico;

    @ManyToOne
    private Categoria categoria;


}
