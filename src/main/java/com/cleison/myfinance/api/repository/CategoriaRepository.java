package com.cleison.myfinance.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleison.myfinance.api.model.Categoria;

/**
 * @author cleison.oliveira
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
