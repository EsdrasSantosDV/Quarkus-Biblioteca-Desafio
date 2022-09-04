package br.com.stefanini.developerup.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.PropertyException;

import br.com.stefanini.developerup.dao.AutorDao;
import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.model.Autor;
import br.com.stefanini.developerup.model.Cliente;
import br.com.stefanini.developerup.parser.AutorParser;
import br.com.stefanini.developerup.parser.ClienteParser;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class AutorService {
	
	@Inject
	AutorDao dao;
	public List<AutorDto> listar(){
		return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
	}
	public void validar(@Valid AutorDto dto) throws  Exception
	{
		//ISNI,nome,email,dataNascimento,biografia
		if(dto==null)
		{
			throw new BadRequestException();
		}

		//LEMBRAR DE VALIDAR O PONTO ||dao.EmailSendoUsado(dto.getEmail())
		validaISNIDuplicado(dto.getISNI());
		if(dto.getEmail()==null||!dto.getEmail().contains("@")||!dto.getEmail().contains("."))
		{
			throw new PropertyException("Email Invalido",dto.getEmail());
		}

		if(dto.getDataNascimento()==null)
		{
			throw  new PropertyException("Data Invalida",dto.getNome());
		}

		if(dto.getNome()==null||dto.getNome().length()>50)
		{
			throw  new PropertyException("Nome Invalido",dto.getNome());
		}

		if(dto.getBiografia()==null||dto.getBiografia().length()>200)
		{
			throw  new PropertyException("Biografia Invalida",dto.getBiografia());
		}

	}

	@Transactional(rollbackOn = Exception.class)
	public void cadastrar(@Valid AutorDto autordto) throws Exception {

		//validar
		validar(autordto);
		Autor autor = AutorParser.get().entidade(autordto);
		try
		{
			dao.cadastrar(autor);
		}
		catch (Exception error)
		{
			this.validaISNIDuplicado(autordto.getISNI());
			throw error;
		}

	}

	private void validaISNIDuplicado(String ISNI) throws PropertyException {
		if(dao.ISNISendoUsado(ISNI)) {
			throw new PropertyException("ISNI already exists",ISNI);
		}
	}
	public AutorDto buscar(String ISNI)
	{
		return AutorParser.get().dto(buscarPorISNI(ISNI));
	}

	@Transactional(rollbackOn = Exception.class)
	public void atualizar(String ISNI, AutorDto dto) {
		Autor autor= AutorParser.get().entidade(dto);
		Autor autorBanco=buscarPorISNI(ISNI);
		autorBanco.setEmail(autor.getEmail());
		autorBanco.setNome(autor.getNome());
		autorBanco.setBiografia(autor.getBiografia());
		autorBanco.setDataNascimento(autor.getDataNascimento());


		dao.atualizar(autorBanco);
	}

	public void excluir(String ISNI)
	{
		//VALIDAR SE O ID E VALIDO
		if(dao.buscarPorISNI(ISNI)==null)
		{
			throw new NotFoundException();
		}
		dao.excluirPorISNI(ISNI);
	}
	private Autor buscarPorISNI(String ISNI)
	{
		Autor autor=dao.buscarPorISNI(ISNI);
		if(autor==null)
		{
			throw new NotFoundException();
		}
		return autor;
	}

}

