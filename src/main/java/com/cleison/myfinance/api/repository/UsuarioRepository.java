package com.cleison.myfinance.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleison.myfinance.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);

}
