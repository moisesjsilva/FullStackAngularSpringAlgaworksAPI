package com.jsm.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsm.service.exceptions.ObjectNotFoundException;
import com.jsm.service.exceptions.ObjetoJaCadastradoException;
import com.jsm.service.exceptions.PessoaInativaException;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler({ObjectNotFoundException.class})
	public ResponseEntity<List<DefaultError>> ObjectNotFoundExceptionHandler(ObjectNotFoundException ex, HttpServletRequest request){
		
		int status = HttpStatus.NOT_FOUND.value();
		DefaultError error = new DefaultError(System.currentTimeMillis(), status, getProperty("msg.not_found"), ex.getMessage(), request.getRequestURI());
		
		List<DefaultError> errors = new ArrayList<>();
		errors.add(error);
		return ResponseEntity.status(status).body(errors);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = request.getContextPath();
		
		DefaultError error = new DefaultError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), getProperty("msg.dados_invalidado"), ex.getMessage(), path);
		
		List<DefaultError> errors = new ArrayList<>();
		errors.add(error);
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ObjetoJaCadastradoException.class})
	public ResponseEntity<Object> objetoJaCadastradoExceptionHandler(ObjetoJaCadastradoException ex,HttpServletRequest request){
		int status = HttpStatus.BAD_REQUEST.value();
		DefaultError error = new DefaultError(System.currentTimeMillis(), status, getProperty("msg.requisicao_invalida"), ex.getMessage(), request.getRequestURI());
			
		
		List<DefaultError> errors = new ArrayList<>();
		errors.add(error);
		return ResponseEntity.status(status).body(errors);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		status = HttpStatus.BAD_REQUEST;
		FieldError error = new FieldError(System.currentTimeMillis(), status.value(), getProperty("msg.requisicao_invalida"), ex.getMessage(), request.getContextPath());
		BindingResult br = ex.getBindingResult();
		for(org.springframework.validation.FieldError f:ex.getBindingResult().getFieldErrors()) {
			error.getFields().add(new Field(f.getField(),f.getDefaultMessage()));
		}
		
		List<FieldError> erros = new ArrayList<>();
		erros.add(error);
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	//*ConstraintViolationException
//	@ExceptionHandler({ConstraintViolationException.class})
//	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,HttpServletRequest request){
//		int status = HttpStatus.BAD_REQUEST.value();
//		DefaultError error = new DefaultError(System.currentTimeMillis(), status, getProperty("msg.requisicao_invalida"), ExceptionUtils.getRootCause(ex).getMessage(), request.getRequestURI());
//			
//		
//		List<DefaultError> errors = new ArrayList<>();
//		errors.add(error);
//		return ResponseEntity.status(status).body(errors);
//	}
	
	
	@ExceptionHandler({PessoaInativaException.class})
	public ResponseEntity<Object> handlePessoaInativaException(PessoaInativaException ex,HttpServletRequest request){
		int status = HttpStatus.BAD_REQUEST.value();
		DefaultError error = new DefaultError(System.currentTimeMillis(), status, getProperty("msg.requisicao_invalida"), ExceptionUtils.getRootCause(ex).getMessage(), request.getRequestURI());
			
		
		List<DefaultError> errors = new ArrayList<>();
		errors.add(error);
		return ResponseEntity.status(status).body(errors);
	}
	private String getProperty(String propName) {
		return messageSource.getMessage(propName,null, Locale.getDefault());
		
	}
}
