package com.jdnevesti.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdnevesti.cursomc.domain.Categoria;
import com.jdnevesti.cursomc.repositories.CategoriaRepository;
import com.jdnevesti.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		if (obj.orElse(null) == null) {
			throw new ObjectNotFoundException ("Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName());
		}
		
		return obj.orElse(null);
	}
}
