package com.jdnevesti.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jdnevesti.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	@NotEmpty(message="O campo nome é de preenchimento obrigatório!")
	@Length(min=2, max=40, message="O tamanho deve ser entre 5 e 50 caracteres!")
	private String nome;
	
	public CidadeDTO() {}
	
	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		nome= obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
