package com.jsm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="lancamento")
public class Lancamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@Column(name="data_vencimento")
	private LocalDate dataVencimento;
	
	@Column(name="data_pagamento")
	private LocalDate dataPagamento;
	
	@NotNull
	private BigDecimal valor;
	
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private LancamentoTipo tipo;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;


	public Lancamento() {
		super();
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public LocalDate getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public LocalDate getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public LancamentoTipo getTipo() {
		return tipo;
	}


	public void setTipo(LancamentoTipo tipo) {
		this.tipo = tipo;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Lancamento other = (Lancamento) obj;
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
		builder.append("Lancamento [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", dataVencimento=");
		builder.append(dataVencimento);
		builder.append(", dataPagamento=");
		builder.append(dataPagamento);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", observacao=");
		builder.append(observacao);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", pessoa=");
		builder.append(pessoa);
		builder.append("]");
		return builder.toString();
	}
	
	

}
