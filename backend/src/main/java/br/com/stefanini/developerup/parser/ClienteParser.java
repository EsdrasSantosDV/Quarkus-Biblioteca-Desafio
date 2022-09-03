package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.ClienteDto;
import br.com.stefanini.developerup.model.Cliente;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
public class ClienteParser {
    public static ClienteParser get(){
        return  new ClienteParser();
    }


    public Cliente entidade(ClienteDto dto)
    {
        Cliente entidade=new Cliente();

        entidade.setEmail(dto.getEmail());
        entidade.setNome(dto.getNome());
        entidade.setContato(dto.getContato());
        return entidade;
    }

    public ClienteDto dto(Cliente entidade){
        ClienteDto dto = new ClienteDto();

        dto.setEmail(entidade.getEmail());
        dto.setNome(entidade.getNome());
        dto.setContato(entidade.getContato());
        return dto;
    }


}
