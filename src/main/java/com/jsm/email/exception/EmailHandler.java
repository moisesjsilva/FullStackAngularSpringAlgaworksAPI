package com.jsm.email.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailHandler {
//EmailNotFound
	@ExceptionHandler
	public ResponseEntity<?> handleEmailNotFound(EmailNotFound ex){
		String result ="{'exception':'"+ex.getMessage()+"'}";
		return ResponseEntity.badRequest().body(result);
	}
}
