package com.jsm.security.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Perfil.class)
public abstract class Perfil_ {

	public static volatile SingularAttribute<Perfil, String> sigla;
	public static volatile ListAttribute<Perfil, Role> roles;
	public static volatile SingularAttribute<Perfil, String> nome;
	public static volatile SingularAttribute<Perfil, Long> id;

}

