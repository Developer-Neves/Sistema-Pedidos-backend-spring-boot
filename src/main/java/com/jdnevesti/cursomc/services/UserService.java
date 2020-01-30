package com.jdnevesti.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdnevesti.cursomc.security.UserSS;

@Service
public class UserService {
	// Quem é o usuário logado?
	public static UserSS authenticated() {
		try {
			// Retorna o usuário logado no sistema
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
