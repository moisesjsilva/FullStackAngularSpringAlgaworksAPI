package com.jsm.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsm.event.RecursoCriadoEvent;
import com.jsm.service.Service;

public abstract class ResourceImpl<T,S extends Service<T>> implements Resource<T> {

	@Autowired
	private S service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	@Override
	public ResponseEntity<T> save(@Valid @RequestBody T object, HttpServletResponse response) {
		object = service.save(object);
		
		Long id = service.getId(object);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//				.path("/{id}")
//				.buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(object);
	}

	@GetMapping
	@Override
	public ResponseEntity<List<T>> findAll() {
		List<T> lista = service.findAll();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/p")
	@Override
	public ResponseEntity<Page<T>> findAll(Pageable pageable) {
		Page<T> page = service.findAll(pageable);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<T> findById(@PathVariable Long id) {
		T object = service.getOne(id);
		return ResponseEntity.ok(object);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.findById(id);
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<T> update(@PathVariable Long id,@Valid @RequestBody T object) {
		T t = service.update(id, object);
		return ResponseEntity.ok(t);
	}

}
