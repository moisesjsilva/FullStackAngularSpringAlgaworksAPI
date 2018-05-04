package com.jsm.error;

import java.util.ArrayList;
import java.util.List;

public class FieldError extends DefaultError {
	List<Field> fields = new ArrayList<>();

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public FieldError() {
		super();
		
	}

	public FieldError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		
	}
	
	
}
