package com.financas.FinancaTeste.repository;

import com.financas.FinancaTeste.model.ListaProduto;
import com.financas.FinancaTeste.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaProdutoRepository extends JpaRepository<ListaProduto, Long>{
}
