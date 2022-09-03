package br.com.stefanini.developerup.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.com.stefanini.developerup.model.Livros;
import io.quarkus.panache.common.Sort;

@RequestScoped
public class LivrosDao {
	
	public List<Livros> listar(){
        return Livros.listAll(Sort.by("ISBN,nome,anoDePublicacao,editora, quantidadeExemplares").ascending());
    }

}
