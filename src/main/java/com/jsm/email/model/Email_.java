package com.jsm.email.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Email.class)
public abstract class Email_ {

	public static volatile SingularAttribute<Email, String> password;
	public static volatile SingularAttribute<Email, Integer> port;
	public static volatile SingularAttribute<Email, String> host;
	public static volatile SingularAttribute<Email, Long> id;
	public static volatile SingularAttribute<Email, String> tag;
	public static volatile SingularAttribute<Email, String> username;

}

