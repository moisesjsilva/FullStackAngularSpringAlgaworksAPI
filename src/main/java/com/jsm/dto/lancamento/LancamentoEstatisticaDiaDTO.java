package com.jsm.dto.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jsm.model.LancamentoTipo;

public class LancamentoEstatisticaDiaDTO {
	private LancamentoTipo tipo;
	private LocalDate dia;
	private BigDecimal total;

	public LancamentoEstatisticaDiaDTO(LancamentoTipo tipo, LocalDate dia, BigDecimal total) {
		super();
		this.tipo = tipo;
		this.dia = dia;
		this.total = total;
	}

	public LancamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(LancamentoTipo tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
