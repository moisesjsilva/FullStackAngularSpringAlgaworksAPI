package com.jsm.dto.lancamento;

import java.math.BigDecimal;

import com.jsm.model.LancamentoTipo;
import com.jsm.model.Pessoa;



public class LancamentoEstatisticaPessoaDTO {
	private LancamentoTipo tipo;
	private Pessoa pessoa;
	private BigDecimal total;

	public LancamentoEstatisticaPessoaDTO(LancamentoTipo tipo, Pessoa pessoa, BigDecimal total) {
		super();
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.total = total;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
