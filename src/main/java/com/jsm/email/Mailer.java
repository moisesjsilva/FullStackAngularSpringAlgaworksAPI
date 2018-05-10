package com.jsm.email;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.jsm.email.model.Email;
import com.jsm.email.model.EmailContent;
import com.jsm.email.service.EmailService;
import com.jsm.model.Lancamento;
import com.jsm.service.LancamentoService;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private LancamentoService lancService;
	
	
//	@EventListener()
//	public void enviarTeste(ApplicationReadyEvent event) {
//		List<String> as = new ArrayList<>();
//		as.add("jsilva.moises@gmail.com");
//		this.sendFromApacheCommons("jsilva.moises@gmail.com", as, "Teste de Enviao de email", "Olá<br/>Funcionando.");
//	}
	
	
//	@EventListener()
//	public void enviarTeste(ApplicationReadyEvent event) {
//		List<String> as = new ArrayList<>();
//		as.add("jsilva.moises@gmail.com");
//		
//		
//		List<Lancamento> lancamentos = lancService.findAll();
//		EmailContent eContent = new EmailContent();
//		eContent.setAssunto("Teste")
//		.setDestinatarios(as)
//		.setRemententeEmail("jsilva.moises@gmail.com")
//		.setTemplatePath("email/aviso_lancamentos_vencidos")
//		.putObject("lancamentos", lancamentos)
//		.putObject("empresa", "JSM Mobile Associations LTDA");
//		
//		sendEmail(eContent);
//	}
	

	public void enviarEmail(String rementente, List<String> destinatarios, String assunto, String mensagem) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

			helper.setFrom(rementente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);			
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problema ao enviar email.", e);
		}

	}
	
	
	
	public void sendEmail(EmailContent emailContent) {
		Context context = new Context(new Locale("pt","BR"));
		
		context.setVariables(emailContent.getObjsMap());
		
		String mensagem = thymeleaf.process(emailContent.getTemplatePath(), context);
		
		this.sendFromApacheCommons(emailContent.getRemententeEmail(), emailContent.getDestinatarios(), emailContent.getAssunto(), mensagem);
	}
	
	
	public void sendFromApacheCommons(String rementente, List<String> destinatarios, String assunto, String mensagem) {
		HtmlEmail htmlEmail = new HtmlEmail();
		System.out.println("Iniciando envio de email.");
		Email email = emailService.findByTag("default");
		//System.out.println(email);
		Collection<InternetAddress> ccs = new ArrayList<>();
		for(String cc:destinatarios) {
			try {
				ccs.add(new InternetAddress(cc));
			} catch (AddressException e) {
				throw new RuntimeException("Erro a definir copias do email.",e);
				
			}
		}
		
		try {
			
			
			htmlEmail.setFrom(rementente);
			htmlEmail.setSubject(assunto);
			htmlEmail.setHtmlMsg(mensagem);
			htmlEmail.addTo(destinatarios.get(0));
			htmlEmail.setCc(ccs);
			
			
			
			
			htmlEmail.setHostName(email.getHost());
			htmlEmail.setSmtpPort(587);
			htmlEmail.setTLS(true);
			htmlEmail.setSSL(true);                
			htmlEmail.setAuthenticator(new DefaultAuthenticator(email.getUsername(), email.getPassword()));            
			htmlEmail.send();
			//System.out.println("Email enviado.");
		} catch (EmailException  e) {
			throw new RuntimeException("Problema ao enviar email.", e);
		}
		
	}
}
