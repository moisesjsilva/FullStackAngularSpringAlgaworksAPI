package com.jsm.email.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsm.email.exception.EmailNotFound;
import com.jsm.email.model.Email;
import com.jsm.email.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
    private EmailRepository repository;
	
	public Email save(Email email) {
		email = repository.save(email);
		return email;
	}
	
	
	public Email findById(Long id) {
		Optional<Email> savedEmail = repository.findById(id);
		if(!savedEmail.isPresent()) {
			throw new EmailNotFound("Email Não encontrado com id: "+id);
		}
		
		return savedEmail.get();
	}
	
	
	public Email findByTag(String tag) {
		Optional<Email> savedEmail = repository.findByTagIgnoreCase(tag);
		if(!savedEmail.isPresent()) {
			throw new EmailNotFound("Email Não encontrado com tag: "+tag);
		}
		
		return savedEmail.get();
	}
	
	public Email update(Long id,Email email) {
		Email savedEmail = findById(id);
		
		BeanUtils.copyProperties(email, savedEmail, "id");
			
		savedEmail = repository.save(email);
		return email;
	}
	
	public void delete(Long id) {
		findById(id);// Verifica se o email existe para lançar um exception caso não exista.
		repository.deleteById(id);
	}
	
	public List<Email> findAll(){
		return repository.findAll();
	}
}
