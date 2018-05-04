package com.jsm.resorce;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface Resource<T> {
	
	public ResponseEntity<T> save(T object,HttpServletResponse response);

	public ResponseEntity<List<T>> findAll();
	
	public ResponseEntity<Page<T>> findAll(Pageable pageable);
	
	public ResponseEntity<T> findById(Long id);
	
	public ResponseEntity<Void> deleteById(Long id);
	
	public ResponseEntity<T> update(Long id, T object);
}
