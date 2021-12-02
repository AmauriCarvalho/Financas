package com.financas.FinancaTeste.repository;

import com.financas.FinancaTeste.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //@Query("select p from Produto p order by p.nome_prod")
    //public List<Produto> findByNomeProduto();
}
