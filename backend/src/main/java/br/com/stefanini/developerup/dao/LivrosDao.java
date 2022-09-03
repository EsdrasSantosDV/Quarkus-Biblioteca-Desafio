package br.com.stefanini.developerup.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.stefanini.developerup.model.Cliente;
import br.com.stefanini.developerup.model.Livros;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class LivrosDao {


    public List<Livros> listar(){
        return Livros.listAll(Sort.by("ISBN,nome,anoDePublicacao,editora, quatidadeExemplares").ascending());
    }

    @Transactional
    public void cadastrar(Livros livros)
    {
        livros.persist();
    }

    





}
