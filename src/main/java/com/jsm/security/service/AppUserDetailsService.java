//package com.jsm.security.service;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.jsm.security.model.Usuario;
//import com.jsm.security.repository.UsuarioRepository;
//
//@Service
//public class AppUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UsuarioRepository repository;
//	
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		System.out.println(username);
//		Optional<Usuario> u = repository.findByUsername(username);
//		 Usuario usuario =u.orElseThrow(()-> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
//	
//		
//		 
//		 
//		 return new User(username, usuario.getPassword(), getAuthorities(usuario));
//	}
//
//      
//	private Collection<? extends GrantedAuthority> getAuthorities(Usuario user) {
//		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//		
//		user.getPerfil().getRoles().forEach(u-> authorities.add(new SimpleGrantedAuthority(u.getRole().toUpperCase())));
//		
//		return authorities;
//	}
//
//}
