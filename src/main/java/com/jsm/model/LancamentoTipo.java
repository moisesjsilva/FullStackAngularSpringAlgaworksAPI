package com.jsm.model;

public enum LancamentoTipo {
	RECEITA(1, "Receita"), DESPESA(2, "Despesa");

	private Integer id;
	private String descricao;

	private LancamentoTipo(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
