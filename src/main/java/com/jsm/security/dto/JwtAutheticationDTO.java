package com.jsm.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JwtAutheticationDTO {

	private String username;
	private String password;

	public JwtAutheticationDTO() {
	}

	@NotEmpty(message = "Email não pode ser vazio.")
	//@Email(message = "Email inválido.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + username + ", senha=" + password + "]";
	}

}