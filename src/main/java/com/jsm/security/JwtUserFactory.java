package com.jsm.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jsm.security.model.Usuario;
import com.jsm.security.service.JwtUser;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getUsername(), usuario.getPassword(),usuario.getFoto(),
				mapToGrantedAuthorities(usuario));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Usuario usuario) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		
		usuario.getPerfil().getRoles()
				.forEach(u -> authorities.add(new SimpleGrantedAuthority(u.getRole().toUpperCase())));
		
		for(GrantedAuthority a:authorities) {
			System.out.println(a.getAuthority());
		}

		return authorities;
	}
}