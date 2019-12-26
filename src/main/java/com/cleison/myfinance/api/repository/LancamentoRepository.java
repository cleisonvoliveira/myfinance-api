package com.cleison.myfinance.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleison.myfinance.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
