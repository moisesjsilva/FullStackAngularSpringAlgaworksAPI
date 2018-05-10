package com.jsm.email.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.email.model.Email;
import com.jsm.email.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailResource {
	
	@Autowired
	private EmailService service;
	
	
	@PostMapping
	public ResponseEntity<Email> save(@Valid @RequestBody Email email){
		email = service.save(email);
		return ResponseEntity.ok(email);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Email> update(@PathVariable Long id,@Valid @RequestBody Email email){
		email = service.update(id, email);
		return ResponseEntity.ok(email);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Email>> findAll(){
		List<Email> lista = service.findAll();
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(params="tag")
	public ResponseEntity<Email> findAll(@RequestParam(name="tag",defaultValue="default")String tag){
		Email email = service.findByTag(tag);		
		return ResponseEntity.ok(email);
	}

}
