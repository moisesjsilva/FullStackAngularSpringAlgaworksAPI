package com.jsm.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.model.Lancamento;
import com.jsm.model.filter.LancamentoFilter;
import com.jsm.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends ResourceImpl<Lancamento, LancamentoService> {

	@Autowired
	private LancamentoService service;
	
	@GetMapping("/pesquisa")
	public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable pageable){
		Page<Lancamento> page = service.pesquisar(filter, pageable);		
		return ResponseEntity.ok(page);
	}
}
