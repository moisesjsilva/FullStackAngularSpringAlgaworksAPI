package com.jsm.service;




import org.springframework.beans.BeanUtils;
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
	
	@Override
	public Pessoa save(Pessoa object) {
		object.getContatos().forEach(c-> c.setPessoa(object));
		return super.save(object);
	}
	
	
	@Override
	public Pessoa update(Long id, Pessoa pessoa) {
		
		Pessoa pessoaSalva = findById(id).get();
		
		pessoaSalva.getContatos().clear();
		pessoaSalva.getContatos().addAll(pessoa.getContatos());
		pessoaSalva.getContatos().forEach(c-> c.setPessoa(pessoaSalva));
		
		BeanUtils.copyProperties(pessoa, pessoaSalva,"id","contatos");
		
		return getRepository().save(pessoaSalva);
	}
	
	@Override
	public void delete(Pessoa object) {
		super.delete(object);
	}
}
