package com.jsm.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jsm.dto.lancamento.LancamentoEstatisticaCategoriaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaDiaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaPessoaDTO;
import com.jsm.model.Lancamento;
import com.jsm.model.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<LancamentoEstatisticaCategoriaDTO> porCategoria(LocalDate mesReferncia);
	
	public List<LancamentoEstatisticaDiaDTO> porDia(LocalDate mesReferncia);
	
	public List<LancamentoEstatisticaPessoaDTO> porPessoa(LocalDate inicio, LocalDate fim);
	
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
}
