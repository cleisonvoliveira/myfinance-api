package com.cleison.myfinance.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleison.myfinance.api.model.Lancamento;
import com.cleison.myfinance.api.repository.lancamento.LancamentoRepositoryQuery;

/**
 * @author cleison.oliveira
 *
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
