package com.financas.FinancaTeste.repository;

import com.financas.FinancaTeste.model.ListaServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaServicoRepository extends JpaRepository<ListaServico, Long> {
}
