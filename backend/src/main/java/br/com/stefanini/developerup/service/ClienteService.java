package br.com.stefanini.developerup.service;



import br.com.stefanini.developerup.dao.ClienteDao;
import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.model.Cliente;
import br.com.stefanini.developerup.parser.ClienteParser;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.xml.bind.PropertyException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@RequestScoped
@Traced
public class ClienteService {
    @Inject
    ClienteDao dao;


    public List<ClienteDto> listar(){
        return dao.listar().stream().map(ClienteParser.get()::dto).collect(Collectors.toList());
    }


    public void validar(@Valid ClienteDto dto) throws  Exception
    {
        if(dto==null)
        {
            throw new BadRequestException();
        }
        //LEMBRAR DE VALIDAR O PONTO ||dao.EmailSendoUsado(dto.getEmail())

        validaEmailDuplicado(dto.getEmail());

        if(dto.getEmail()==null||!dto.getEmail().contains("@")||!dto.getEmail().contains("."))
        {
            throw new PropertyException("Email Invalido",dto.getEmail());
        }
        if(dto.getContato()==null)
        {
            throw new PropertyException("Telefone Invalido",dto.getContato());
        }
        if(dto.getNome()==null||dto.getNome().length()>50)
        {
            throw  new PropertyException("Nome Invalido",dto.getNome());
        }


    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrar(@Valid  ClienteDto clientedto) throws Exception {

        //validar
        validar(clientedto);
        Cliente cliente = ClienteParser.get().entidade(clientedto);
        try
        {
            dao.cadastrar(cliente);
        }
        catch (Exception error)
        {
            this.validaEmailDuplicado(clientedto.getEmail());
            throw error;
        }

    }


    private void validaEmailDuplicado(String email) throws PropertyException {
        if(dao.EmailSendoUsado(email)) {
            throw new PropertyException("Email already exists", email);
        }
    }

    public ClienteDto buscar(String email)
    {
        return ClienteParser.get().dto(buscarPorEmail(email));
    }



    @Transactional(rollbackOn = Exception.class)
    public void atualizar(String email, ClienteDto dto) {
        Cliente cliente=ClienteParser.get().entidade(dto);
        Cliente clienteBanco=buscarPorEmail(email);
        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setContato(cliente.getContato());
        dao.atualizar(clienteBanco);
    }

    public void excluir(String email)
    {
        //VALIDAR SE O ID E VALIDO
        if(dao.buscarPorEmail(email)==null)
        {
            throw new NotFoundException();
        }
        dao.excluirPorEmail(email);
    }
    private Cliente buscarPorEmail(String email)
    {
        Cliente cliente=dao.buscarPorEmail(email);
        if(cliente==null)
        {
            throw new NotFoundException();
        }
        return cliente;
    }



}
