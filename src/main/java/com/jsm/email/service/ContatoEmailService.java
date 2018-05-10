package com.jsm.email.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsm.email.exception.EmailNotFound;
import com.jsm.email.model.ContatoEmail;
import com.jsm.email.repository.ContatoEmailRepository;

@Service
public class ContatoEmailService {
	@Autowired
    private ContatoEmailRepository repository;
	
	public ContatoEmail save(ContatoEmail email) {
		email = repository.save(email);
		return email;
	}
	
	
	public ContatoEmail findById(Long id) {
		Optional<ContatoEmail> savedEmail = repository.findById(id);
		if(!savedEmail.isPresent()) {
			throw new EmailNotFound("Email Não encontrado com id: "+id);
		}
		
		return savedEmail.get();
	}
	
	
	public List<ContatoEmail> findByDepartamento(String departamento) {
		List<ContatoEmail> emails = repository.findByDepartamento(departamento);
		return emails;
	}
	
	public ContatoEmail update(Long id,ContatoEmail email) {
		ContatoEmail savedEmail = findById(id);
		
		BeanUtils.copyProperties(email, savedEmail, "id");
			
		savedEmail = repository.save(email);
		return email;
	}
	
	public void delete(Long id) {
		findById(id);// Verifica se o email existe para lançar um exception caso não exista.
		repository.deleteById(id);
	}
	
	public List<ContatoEmail> findAll(){
		return repository.findAll();
	}
}
