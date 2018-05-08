package com.jsm.security.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.security.model.Usuario;
import com.jsm.security.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("/u/{username}")
	public ResponseEntity<Usuario> findByUsername(@PathVariable String username){
		Usuario user = service.findByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Usuario> updateStatus(Long id){
		Usuario u = service.updateStatus(id);
		return ResponseEntity.ok(u);
	}
	
	@GetMapping()
	public ResponseEntity<Page<Usuario>> findAll(Pageable pageable){
		Page<Usuario> page = service.findAll(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario u = service.findById(id);
		return ResponseEntity.ok(u);
	}
	
}
