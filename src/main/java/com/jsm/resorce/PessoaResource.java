package com.jsm.resorce;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
