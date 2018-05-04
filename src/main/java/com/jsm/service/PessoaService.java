package com.jsm.service;



import org.springframework.stereotype.Service;

import com.jsm.model.Pessoa;
import com.jsm.repository.PessoaRepository;

@Service
public class PessoaService extends ServiceImpl<Pessoa,PessoaRepository> {

	public Pessoa updateStatus(Long id) {
		Pessoa pessoa = getOne(id);
		pessoa.setAtivo(pessoa.getAtivo()?false:true);
		pessoa = getRepository().save(pessoa);
		return pessoa;
	}
}
