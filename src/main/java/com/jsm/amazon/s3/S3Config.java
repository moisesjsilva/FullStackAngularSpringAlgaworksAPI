package com.jsm.amazon.s3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;
import com.jsm.amazon.s3.controller.AmazonAppRepository;
import com.jsm.amazon.s3.model.AmazonApp;

@Configuration
public class S3Config {

	
	@Autowired
	private AmazonAppRepository repository;
	
	@Bean
	public AmazonS3 amazonS3() {
		List<AmazonApp> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			return null;
		}
		AmazonApp app = lista.get(0);
		System.out.println(app==null);
		System.out.println(app);
		AWSCredentials credentials = new BasicAWSCredentials(app.getAwsAccessKeyId(), app.getAwsSecretKey());
		
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withRegion(app.getAwsRegion())
				.withCredentials(new AWSStaticCredentialsProvider(credentials))				
				.build();
		
		//s3.deleteBucket(app.getAwsBucket());
		
		if(!s3.doesBucketExistV2(app.getAwsBucket())) {
			s3.createBucket(new CreateBucketRequest(app.getAwsBucket()));
			
			BucketLifecycleConfiguration.Rule regraExpiracao = 
					new BucketLifecycleConfiguration.Rule()
					.withId("RegraExpiracaoTemporaryFile")
					.withFilter(new LifecycleFilter(
							new LifecycleTagPredicate(new Tag("expirar","true"))))
					.withExpirationInDays(1)
					.withStatus(BucketLifecycleConfiguration.ENABLED);
			
			BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration()
					.withRules(regraExpiracao);
			
			s3.setBucketLifecycleConfiguration(app.getAwsBucket(),configuration);
		}
		
		return s3;
	}
}
