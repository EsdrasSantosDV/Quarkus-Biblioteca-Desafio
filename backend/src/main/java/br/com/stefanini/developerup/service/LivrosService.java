package br.com.stefanini.developerup.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.stefanini.developerup.dao.LivrosDao;
import br.com.stefanini.developerup.dto.LivrosDto;
import br.com.stefanini.developerup.parser.LivroParser;

@RequestScoped
public class LivrosService {
	    @Inject
	    LivrosDao dao;

	    public List<LivrosDto> listar(){
	        return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<LivrosDto> cadastrar(){
	        return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<LivrosDto> editar(){
	        return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<LivrosDto> deletar(){
	        return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList());
	    }

}
