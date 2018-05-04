package com.jsm.service;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsm.model.Categoria;
import com.jsm.repository.CategoriaRepository;
import com.jsm.service.exceptions.ObjetoJaCadastradoException;

@Service
public class CategoriaService extends ServiceImpl<Categoria,CategoriaRepository> {

	@Override
	public Categoria save(Categoria object) {
		Optional<Categoria> cat = getRepository().findByNome(object.getNome());
		if(cat.isPresent()) {
			throw new ObjetoJaCadastradoException("Uma categoria com nome "+object.getNome()+" já foi cadastrada!");
		}
		return super.save(object);
	}

	
}
