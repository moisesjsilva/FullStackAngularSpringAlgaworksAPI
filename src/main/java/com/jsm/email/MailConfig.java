package com.jsm.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.jsm.email.model.Email;
import com.jsm.email.service.EmailService;

@Configuration
public class MailConfig {
	
	@Autowired
	private EmailService emailService;
	
	
	

	@Bean
	public JavaMailSender javaMailSender() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000); 
		

		
		Email email = emailService.findByTag("default");
		
		System.out.println(email);
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(props);
		mailSender.setHost("smtp.googlemail.com");
		mailSender.setPort(email.getPort());
		mailSender.setUsername(email.getUsername());
		mailSender.setPassword(email.getPassword());
		
		
		return mailSender;
	}
	

}
