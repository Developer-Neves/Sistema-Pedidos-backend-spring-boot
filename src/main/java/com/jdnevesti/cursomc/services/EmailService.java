package com.jdnevesti.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.jdnevesti.cursomc.domain.Cliente;
import com.jdnevesti.cursomc.domain.Pedido;

public interface EmailService {
	
	//Email no formato texto
	void sendOrderConfirmationEmail(Pedido obj);	
	void sendEmail(SimpleMailMessage msg);
	
	//Email no formato HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
