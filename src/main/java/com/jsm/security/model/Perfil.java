package com.jsm.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="perfil_role",
	joinColumns= @JoinColumn(name="perfil_id"),
	inverseJoinColumns= @JoinColumn(name="role_id")
	)
	
	private List<Role> roles = new ArrayList<>();
	
	
	public Perfil() {
		super();
	}
	public Perfil(Long id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Perfil [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sigla=");
		builder.append(sigla);
		builder.append("]");
		return builder.toString();
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	

}
