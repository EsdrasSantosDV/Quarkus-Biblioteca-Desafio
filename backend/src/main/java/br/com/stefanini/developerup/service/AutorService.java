package br.com.stefanini.developerup.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.stefanini.developerup.dao.AutorDao;
import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.parser.AutorParser;

@RequestScoped
public class AutorService {
	
	    @Inject
	    AutorDao dao;

	    public List<AutorDto> listar(){
	        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<AutorDto> cadastrar(){
	        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<AutorDto> editar(){
	        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
	    }
	    
	    public List<AutorDto> deletar(){
	        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
	    }
	}

