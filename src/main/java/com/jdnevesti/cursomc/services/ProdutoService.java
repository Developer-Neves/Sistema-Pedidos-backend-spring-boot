package com.jdnevesti.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jdnevesti.cursomc.domain.Categoria;
import com.jdnevesti.cursomc.domain.Produto;
import com.jdnevesti.cursomc.repositories.CategoriaRepository;
import com.jdnevesti.cursomc.repositories.ProdutoRepository;
import com.jdnevesti.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);		
		if (obj.orElse(null) == null) {
			throw new ObjectNotFoundException ("Objeto não encontrado! Id " + id + ", Tipo: " + Produto.class.getName());
		}		
		return obj.orElse(null);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
	
}
