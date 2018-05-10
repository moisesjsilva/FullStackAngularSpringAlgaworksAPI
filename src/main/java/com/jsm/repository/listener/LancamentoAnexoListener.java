package com.jsm.repository.listener;

import javax.persistence.PostLoad;

import org.flywaydb.core.internal.util.StringUtils;

import com.jsm.AlgamonayApiApplication;
import com.jsm.amazon.s3.storange.S3;
import com.jsm.model.Lancamento;

public class LancamentoAnexoListener {
	
	

	@PostLoad
	public void postLoad(Lancamento lancamento) {
	    if(StringUtils.hasText(lancamento.getAnexo())) {
	    	S3 s3 = AlgamonayApiApplication.getBean(S3.class);
	    	lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));
	    }
     }
}
