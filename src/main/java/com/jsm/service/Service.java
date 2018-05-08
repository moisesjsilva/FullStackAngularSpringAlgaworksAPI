package com.jsm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Service<T> {
	
	public T save(T object);
    
	public Optional<T> findById(Long id);
	
	public T getOne(Long id);
	
	public List<T> findAll();
	
	public Page<T> findAll(Pageable pageable);
	
	public T update(Long id,T object);
	
	public void deleteById(Long id);
	
	public void delete(T object);
	
	public Long getId(T object);
}
