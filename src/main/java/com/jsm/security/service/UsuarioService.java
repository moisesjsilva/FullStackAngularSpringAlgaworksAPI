package com.jsm.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.security.exception.UsuarioNaoEncontradoException;
import com.jsm.security.model.Usuario;
import com.jsm.security.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario findById(Long id) {
		Optional<Usuario> usuarioOptional = repository.findById(id);

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}

		throw new UsuarioNaoEncontradoException("Usuário não encontrado com id: " + id);
	}

	public Usuario findByUsername(String username) {
		Optional<Usuario> usuarioOptional = repository.findByUsername(username);

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}

		throw new UsuarioNaoEncontradoException("Usuário não encontrado com username: " + username);
	}
	
	
	
	public Usuario updateStatus(Long id) {
		Usuario u = findById(id);
		u.setEnable(u.getEnable()?false:true);
		u = repository.saveAndFlush(u);
		return u;
	}
	
	
	public Page<Usuario> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	

}
