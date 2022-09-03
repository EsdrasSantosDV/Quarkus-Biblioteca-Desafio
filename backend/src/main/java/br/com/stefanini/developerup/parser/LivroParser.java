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
        entidade.setISBN(dto.getISBN());
        entidade.setAnoDePublicacao(dto.getAnoDePublicacao());
        entidade.setEditora(dto.getEditora());
        entidade.setNome(dto.getNome());
        entidade.setQuatidadeExemplares(dto.getQuatidadeExemplares());

        return entidade;
    }

    public LivrosDto dto(Livros entidade){
        LivrosDto dto = new LivrosDto();
        dto.setISBN(entidade.getISBN());
        dto.setAnoDePublicacao(entidade.getAnoDePublicacao());
        dto.setEditora(entidade.getEditora());
        dto.setNome(entidade.getNome());
        dto.setISBN(entidade.getISBN());
        return dto;
    }
}
