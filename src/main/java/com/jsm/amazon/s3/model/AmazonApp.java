package com.jsm.amazon.s3.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="amazon_app")
public class AmazonApp implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="aws_access_key_id")
	private String awsAccessKeyId;
	
	@Column(name="aws_secret_key")
	private String awsSecretKey;
	
	@Column(name="aws_bucket")
	private String awsBucket;
	
	@Column(name="aws_region")
	private String awsRegion;
	
	public AmazonApp() {
		super();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}

	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsBucket() {
		return awsBucket;
	}

	public void setAwsBucket(String awsBucket) {
		this.awsBucket = awsBucket;
	}
	
	

	public String getAwsRegion() {
		return awsRegion;
	}

	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmazonApp [id=");
		builder.append(id);
		builder.append(", awsAccessKeyId=");
		builder.append(awsAccessKeyId);
		builder.append(", awsSecretKey=");
		builder.append(awsSecretKey);
		builder.append(", awsBucket=");
		builder.append(awsBucket);
		builder.append(", awsRegion=");
		builder.append(awsRegion);
		builder.append("]");
		return builder.toString();
	}

	
	
	

}
