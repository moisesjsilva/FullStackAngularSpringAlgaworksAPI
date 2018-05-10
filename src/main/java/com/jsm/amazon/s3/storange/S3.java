package com.jsm.amazon.s3.storange;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SetObjectAclRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;
import com.jsm.amazon.s3.controller.AmazonAppRepository;
import com.jsm.amazon.s3.dto.Anexo;
import com.jsm.amazon.s3.model.AmazonApp;

@Component
public class S3 {
	private static final Logger logger = LoggerFactory.getLogger(S3.class);

	@Autowired
	private AmazonS3 as3;

	@Autowired
	private AmazonAppRepository repository;
	
	private AmazonApp app;
	
	

	

	public String uploadTemp(MultipartFile file) {
		app = getApp();		
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());

		String nomeUnico = uniqueFileName(file.getOriginalFilename());
		

		try {
			PutObjectRequest request = new PutObjectRequest(app.getAwsBucket(), nomeUnico, file.getInputStream(),
					metadata).withAccessControlList(acl);
			
			request.setTagging(new ObjectTagging(Arrays.asList(new Tag("expirar","true"))));
			
			as3.putObject(request);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Arquivo {} enviado com sucesso!",file.getOriginalFilename());
			}
			
			return nomeUnico;
		} catch (IOException e) {
			throw new RuntimeException("Problema ao envia o arquivo para o S3");
		}

	}

	private AmazonApp getApp() {
		List<AmazonApp> lista= repository.findAll();
		
			
			
			if (lista.isEmpty()) {
	            throw new RuntimeException("Não foi localizado a configuração do amazon s3, para envio de arquivos.");
				
			}
			
		
        return lista.get(0);
		
	}
	
	public String configurarUrl(String object) {
		app = getApp();		
		//s3-sa-east-1.amazonaws.com/
			return "\\\\"+app.getAwsBucket()+".s3.amazonaws.com/"+object;
	}

	public String uniqueFileName(String name) {
		return UUID.randomUUID().toString() + "_" + name;
	}

	public void persistFile(String object) {
		app = getApp();		
		SetObjectTaggingRequest sotr =  new SetObjectTaggingRequest(app.getAwsBucket(), object, new ObjectTagging(Collections.emptyList()));
		as3.setObjectTagging(sotr);
	}

	public void removeFile(String anexo) {
		app = getApp();		
		DeleteObjectRequest delete =new DeleteObjectRequest(app.getAwsBucket(), anexo);
		
		as3.deleteObject(delete);
	}

	public void replace(String antigo, String novo) {
		if(StringUtils.hasText(antigo)) {
			this.removeFile(antigo);
		}
		
		
		persistFile(novo);
		
	}
}
