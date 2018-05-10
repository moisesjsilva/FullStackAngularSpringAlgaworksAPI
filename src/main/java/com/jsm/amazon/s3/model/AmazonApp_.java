package com.jsm.amazon.s3.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AmazonApp.class)
public abstract class AmazonApp_ {

	public static volatile SingularAttribute<AmazonApp, String> awsBucket;
	public static volatile SingularAttribute<AmazonApp, String> awsRegion;
	public static volatile SingularAttribute<AmazonApp, String> awsAccessKeyId;
	public static volatile SingularAttribute<AmazonApp, Long> id;
	public static volatile SingularAttribute<AmazonApp, String> awsSecretKey;

}

