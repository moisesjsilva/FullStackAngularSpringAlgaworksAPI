//package com.jsm.service;
//
//
//
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.jsm.security.model.Usuario;
//import com.jsm.security.repository.UsuarioRepository;
//import com.jsm.service.exceptions.ObjectNotFoundException;
//
//
//
//
//
//@Service
//public class UsuarioService extends ServiceImpl<Usuario,UsuarioRepository> {
//
//	public Optional<Usuario> findByUsernameAndEnableTrue(String username) {
//		Optional<Usuario> u = getRepository().findByUsername(username);
//		
//		if(!u.isPresent()) {
//			throw new ObjectNotFoundException("O Login enviado não está cadastrado ou está inativo. "+username);
//		}
//		
//		return u;
//	}
//	
//}
