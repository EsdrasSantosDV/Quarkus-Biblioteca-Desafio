package br.com.stefanini.developerup.parser;

import br.com.stefanini.developerup.dto.LivrosDto;
import br.com.stefanini.developerup.model.Livros;

public class LivroParser {

	public static LivroParser get(){
        return  new LivroParser();
    }

    public Livros entidade(LivrosDto dto)
    {
        Livros entidade=new Livros();
        entidade.setNome(dto.getNome());
        entidade.setISBN(dto.getISBN());
        return entidade;
    }

    public LivrosDto dto(Livros entidade){
        LivrosDto dto = new LivrosDto();

        dto.setNome(entidade.getNome());
        dto.setISBN(entidade.getISBN());
        return dto;
    }
}
