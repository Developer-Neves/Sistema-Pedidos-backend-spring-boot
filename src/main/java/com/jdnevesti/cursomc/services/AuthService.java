package com.jdnevesti.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdnevesti.cursomc.domain.Cliente;
import com.jdnevesti.cursomc.repositories.ClienteRepository;
import com.jdnevesti.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pwe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não cadastrado");
		}
		String newPass = newPassword();
		cliente.setSenha(pwe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char)(rand.nextInt(10) + 48); // o número 48 corresponde ao número "0" na tabela unicode - https://unicode-table.com/pt/
		}
		else if (opt == 1) { // gera letra maiúscula
			return (char)(rand.nextInt(26) + 65); // o número 65 corresponde a letra "A" (maiúscula) na tabela unicode - https://unicode-table.com/pt/
		}
		else { // gera letra minúscula
			return (char)(rand.nextInt(26) + 97); // o número 97 corresponde a letra "a" (minúscula) na tabela unicode - https://unicode-table.com/pt/
		}
	}
}
