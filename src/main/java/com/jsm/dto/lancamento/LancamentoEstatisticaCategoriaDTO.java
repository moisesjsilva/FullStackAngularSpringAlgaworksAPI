package com.jsm.dto.lancamento;

import java.math.BigDecimal;

import com.jsm.model.Categoria;

public class LancamentoEstatisticaCategoriaDTO {
	private Categoria categoria;
	private BigDecimal total;

	public LancamentoEstatisticaCategoriaDTO(Categoria categoria, BigDecimal total) {
		super();
		this.categoria = categoria;
		this.total = total;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
