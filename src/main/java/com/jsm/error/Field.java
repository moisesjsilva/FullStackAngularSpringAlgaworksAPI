package com.jsm.error;

public class Field {

	private String field;
	private String mensagem;
	public Field() {
		super();
		
	}
	public Field(String field, String nome) {
		super();
		this.field = field;
		this.mensagem = nome;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getNome() {
		return mensagem;
	}
	public void setNome(String nome) {
		this.mensagem = nome;
	}
	
	
	
	
}
