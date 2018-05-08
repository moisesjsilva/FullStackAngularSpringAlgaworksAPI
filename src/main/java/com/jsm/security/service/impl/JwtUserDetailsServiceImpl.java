package com.jsm.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsm.security.JwtUserFactory;
import com.jsm.security.model.Usuario;
import com.jsm.security.repository.UsuarioRepository;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuOpt = repository.findByUsername(username);
		if(usuOpt.isPresent()) {
			return  JwtUserFactory.create(usuOpt.get());
		}
		
		throw new UsernameNotFoundException("Username não encontrado!");
	}

}
