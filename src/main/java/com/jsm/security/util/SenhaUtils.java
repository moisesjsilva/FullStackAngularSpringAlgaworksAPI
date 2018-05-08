package com.jsm.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {
    /**
     * Gera Um Hash utilizando BCrypt
     * @param senha
     * @return
     */
	public static String gerarBCrypt(String senha) {
		if(senha == null) {
			return senha;
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	/**
	 * Verifica se a Senha é válida
	 * @param senha
	 * @param senhaEncoded
	 * @return
	 */
	public static boolean isValid(String senha,String senhaEncoded) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senha, senhaEncoded);
	}
	
}
