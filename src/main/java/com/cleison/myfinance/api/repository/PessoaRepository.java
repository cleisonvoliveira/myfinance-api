package com.cleison.myfinance.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleison.myfinance.api.model.Pessoa;

/**
 * @author cleison.oliveira
 *
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
