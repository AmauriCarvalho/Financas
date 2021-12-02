package com.financas.FinancaTeste.repository;

import com.financas.FinancaTeste.model.Conta;
import com.financas.FinancaTeste.model.ListaConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaContaRepository extends JpaRepository<ListaConta, Long> {
}
