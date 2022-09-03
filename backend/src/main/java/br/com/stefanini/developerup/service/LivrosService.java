package br.com.stefanini.developerup.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.stefanini.developerup.dao.LivrosDao;
import br.com.stefanini.developerup.dto.LivrosDto;
import br.com.stefanini.developerup.parser.LivroParser;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class LivrosService {
	    @Inject
	    LivrosDao dao;


}
