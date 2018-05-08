package com.jsm.security.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<String> erros;
	
	
	public Response() {
		super();		
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public List<String> getErros() {
		return erros;
	}
	
	public void setErros(List<String> erros) {
		if(this.erros == null) {
			this.erros = new ArrayList<>();
		}
		this.erros = erros;
	}
	
	
}
