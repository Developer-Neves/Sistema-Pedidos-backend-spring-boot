package com.jdnevesti.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jdnevesti.cursomc.domain.Categoria;
import com.jdnevesti.cursomc.dto.CategoriaDTO;
import com.jdnevesti.cursomc.repositories.CategoriaRepository;
import com.jdnevesti.cursomc.services.exceptions.ObjectNotFoundException;
import com.jdnevesti.cursomc.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	// Serviço para buscar dados por ID
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);		
		if (obj.orElse(null) == null) {
			throw new ObjectNotFoundException ("Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName());
		}		
		return obj.orElse(null);
	}
	
	// Serviço para buscar TODOS os dados
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	// Serviço para inserir dados
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// Serviço para atualizar dados
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	// Serviço para excluir dados
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
