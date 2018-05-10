package com.jsm.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.model.Endereco;
import com.jsm.model.Pessoa;
import com.jsm.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource extends ResourceImpl<Pessoa, PessoaService> {
	
	private static final String ROLE_ADMIN="ROLE_ADMIN";
	private static final String ROLE_CREATE="ROLE_PESSOA_CREATE";
	private static final String ROLE_UPDATE="ROLE_PESSOA_UPDATE";
	private static final String ROLE_DELETE="ROLE_PESSOA_DELETE";
	private static final String ROLE_LIST="ROLE_PESSOA_LIST";

	@Autowired 
	private PessoaService service;
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Pessoa> changueStatus(@PathVariable Long id){
		Pessoa pessoa = service.updateStatus(id);		
		return ResponseEntity.ok(pessoa);
		
	}
	@PutMapping("/{id}/endereco")
	public ResponseEntity<Pessoa> atualizarEndereco(@PathVariable Long id,@Valid @RequestBody Endereco endereco){
		Pessoa pessoa = service.getOne(id);
		pessoa.setEndereco(endereco);
		pessoa = service.save(pessoa);
		
		return ResponseEntity.ok(pessoa);
	}
	@Override
	public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa object, HttpServletResponse response) {
		
		return super.save(object, response);
	}
	@PreAuthorize("hasAnyRole('ADMIN','PESSOA_LIST')")
	@Override
	public ResponseEntity<List<Pessoa>> findAll() {
		
		return super.findAll();
	}
	@Override
	public ResponseEntity<Page<Pessoa>> findAll(Pageable pageable) {
		
		return super.findAll(pageable);
	}
	@Override
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		
		return super.findById(id);
	}
	@Override
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		
		return super.deleteById(id);
	}
	@Override
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa object) {		
		return super.update(id, object);
	}
	@Override
	public PessoaService getService() {		
		return super.getService();
	}
	
	
	
}
