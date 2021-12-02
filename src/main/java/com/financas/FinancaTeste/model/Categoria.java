package com.financas.FinancaTeste.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 1, max = 30)
    private String nome_categoria;

}
