package com.jsm.email.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailContent {
	
	private String assunto;
	
	private String remententeEmail;
	
	private String remententeNome;
	
	private List<String> destinatarios = new ArrayList<>();
	
	private String templatePath;
	
	private Map<String,Object> objsMap = new HashMap<>();

	public EmailContent() {
		super();		
	}

	public String getAssunto() {
		return assunto;
	}

	public EmailContent setAssunto(String assunto) {
		this.assunto = assunto;
		return this;
	}

	public String getRemententeEmail() {
		return remententeEmail;
	}

	public EmailContent setRemententeEmail(String remententeEmail) {
		this.remententeEmail = remententeEmail;
		return this;
	}

	public String getRemententeNome() {
		return remententeNome;
	}

	public EmailContent setRemententeNome(String remententeNome) {
		this.remententeNome = remententeNome;
		return this;
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public EmailContent setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
		return this;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public EmailContent setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
		return this;
	}

	public Map<String, Object> getObjsMap() {
		return objsMap;
	}

	public EmailContent setObjsMap(Map<String, Object> objsMap) {
		this.objsMap = objsMap;
		return this;
	}
	
	public EmailContent putObject(String key,Object obj) {
		this.getObjsMap().put(key, obj);
		return this;
	}
	

}
