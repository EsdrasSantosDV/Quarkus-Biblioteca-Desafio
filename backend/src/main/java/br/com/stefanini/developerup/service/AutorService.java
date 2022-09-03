package br.com.stefanini.developerup.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.stefanini.developerup.dao.AutorDao;
import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.parser.AutorParser;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class AutorService {
	
	    @Inject
	    AutorDao dao;

	}

