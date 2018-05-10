package com.jsm.email.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.email.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {

	public Optional<Email> findByTagIgnoreCase(String tag);
}
