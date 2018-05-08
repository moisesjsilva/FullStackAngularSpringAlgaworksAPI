package com.jsm.security.util;

public class GetRoleByClass {

	public static final String getRoleRead(Class clazz) {
		return clazz.getSimpleName().toLowerCase();
	}
}
