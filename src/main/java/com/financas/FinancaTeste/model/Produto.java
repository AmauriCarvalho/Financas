package com.financas.FinancaTeste.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 1, max = 30)
    private String nome_prod;

    @NotBlank
    private Float valor_prod;
    
    private String unidade;

    private int quantidade_prod;

}
