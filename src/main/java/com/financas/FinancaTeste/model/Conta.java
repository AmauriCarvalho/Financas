package com.financas.FinancaTeste.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tb_conta")
@Getter
@Setter
@NoArgsConstructor
public class Conta extends AbstractEntity{
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 1, max = 30)
    private String nome_conta;

    @NotBlank
    private Float valor_conta;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private Date dataVenc;

    private int codigo_conta;

}
