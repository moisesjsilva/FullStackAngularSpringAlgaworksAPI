package com.jsm.service;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsm.model.Lancamento;
import com.jsm.model.Pessoa;
import com.jsm.model.filter.LancamentoFilter;
import com.jsm.repository.LancamentoRepository;
import com.jsm.service.exceptions.PessoaInativaException;

@Service
public class LancamentoService extends ServiceImpl<Lancamento,LancamentoRepository> {

	@Autowired
	private PessoaService pesService;
	
	@PostMapping
	@Override
	public Lancamento save(@Valid @RequestBody Lancamento object) {
		Pessoa pessoa = pesService.getOne(object.getPessoa().getId());
		
		if(!pessoa.getAtivo()) {
			throw new PessoaInativaException(String.format("O Registro não pode ser salvo porque a pessoa vinculada está inativa. [%d-%s]",pessoa.getId(),pessoa.getNome()));
		}
		return super.save(object);
	}

	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable){
		return getRepository().filtrar(filter, pageable);
	}
}
