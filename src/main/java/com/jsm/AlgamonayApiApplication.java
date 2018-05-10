package com.jsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AlgamonayApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	private static ApplicationContext APPLICATION_CONTEXT;
	
	
	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(AlgamonayApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(encoder.encode("123456"));
		
		
		
	}
	
	
	public static <T>  T getBean(Class<T> type){
		return APPLICATION_CONTEXT.getBean(type);
	}
}
