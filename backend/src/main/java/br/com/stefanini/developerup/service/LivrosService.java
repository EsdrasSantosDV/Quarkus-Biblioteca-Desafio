package br.com.stefanini.developerup.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.stefanini.developerup.dao.LivrosDao;
import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.dto.LivrosDto;
import br.com.stefanini.developerup.model.Cliente;
import br.com.stefanini.developerup.model.Livros;
import br.com.stefanini.developerup.parser.ClienteParser;
import br.com.stefanini.developerup.parser.LivroParser;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class LivrosService {
	    @Inject
	    LivrosDao dao;

	public List<LivrosDto> listar(){
		return dao.listar().stream().map(LivroParser.get()::dto).collect(Collectors.toList());
	}
	@Transactional(rollbackOn = Exception.class)
	public void cadastrar(@Valid LivrosDto livrodto) throws Exception {

		//validar

		Livros livro = LivroParser.get().entidade(livrodto);
		try
		{
			dao.cadastrar(livro);
		}
		catch (Exception error)
		{
			//this.validaEmailDuplicado(clientedto.getEmail());
			throw error;
		}

	}
}
