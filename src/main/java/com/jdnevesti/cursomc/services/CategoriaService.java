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
	
	// Serviço para busca de dados
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);		
		if (obj.orElse(null) == null) {
			throw new ObjectNotFoundException ("Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName());
		}		
		return obj.orElse(null);
	}
	
	// Serviço para inserir de dados
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// Serviço para atualizar de dados
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
