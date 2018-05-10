package com.jsm.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.email.model.ContatoEmail;

@Repository
public interface ContatoEmailRepository extends JpaRepository<ContatoEmail,Long> {

	
	public List<ContatoEmail> findByDepartamento(String departamento);
}
