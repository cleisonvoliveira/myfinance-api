package com.cleison.myfinance.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cleison.myfinance.api.model.Permissao;
import com.cleison.myfinance.api.model.Usuario;
import com.cleison.myfinance.api.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos."));
		
		return new User(usuario.getNome(), usuario.getSenha(), getPermissoes(usuario.getPermissoes()));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(List<Permissao> permissoes) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao())));
		return authorities;
	}

}
