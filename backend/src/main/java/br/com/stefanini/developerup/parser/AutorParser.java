package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.model.Autor;

public class AutorParser {
	
	public static AutorParser get(){
        return  new AutorParser();
    }

    public Autor entidade(AutorDto dto)
    {
        Autor entidade=new Autor();
        entidade.setISNI(dto.getISNI());
        entidade.setBiografia(dto.getBiografia());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        return entidade;
    }

    public AutorDto dto(Autor entidade){
        AutorDto dto = new AutorDto();
        dto.setDataNascimento(entidade.getDataNascimento());
        dto.setBiografia(entidade.getBiografia());
        dto.setISNI(entidade.getISNI());
        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        return dto;
    }

}
