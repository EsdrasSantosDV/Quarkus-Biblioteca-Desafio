package br.com.stefanini.developerup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;

import br.com.stefanini.developerup.model.Autor;
import br.com.stefanini.developerup.model.Cliente;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class AutorDao {

    //INJETAR O CONTEXTO
    @PersistenceContext
    EntityManager em;

    public List<Autor> listar()
    {
        String nomeConsulta="CONSULTAR_AUTORES";
        TypedQuery<Autor> query=em.createNamedQuery(nomeConsulta,Autor.class);
        List<Autor> listaRetorno;
        try{
            listaRetorno=  query.getResultList();
        }catch (NoResultException e){
            listaRetorno=new ArrayList();
        }
        return listaRetorno;
    }


    @Transactional
    public void cadastrar(Autor autor)
    {
        String nomeSql="INSERIR_AUTOR";

        //ISNI,nome,email,dataNascimento,biografia
        Query query=em.createNamedQuery(nomeSql);

        query.setParameter("ISNI",autor.getISNI());
        query.setParameter("email",autor.getEmail());
        query.setParameter("nome",autor.getNome());
        query.setParameter("data_nascimento",autor.getDataNascimento());
        query.setParameter("biografia",autor.getBiografia());

        query.executeUpdate();


    }
    public boolean ISNISendoUsado(String ISNI) {
        String nameQuery = "ISNI_SENDO_USADO";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("ISNI", ISNI);


        return query.getResultList().isEmpty() ? false : true;
    }

    public Autor buscarPorISNI(String ISNI){
        String nomeConsulta="CONSULTAR_AUTOR_BYISNI";
        Autor autor;
        TypedQuery<Autor> query=em.createNamedQuery(nomeConsulta,Autor.class);
        query.setParameter("ISNI",ISNI);

        try{
            autor=  query.getSingleResult();
        }catch (NoResultException e){
            autor=null;
        }

        return autor;

    }

    @Transactional
    public void excluirPorISNI(String ISNI)
    {
        String nomeConsulta="EXCLUIR_AUTOR_BYISNI";
        Query query=em.createNamedQuery(nomeConsulta);
        //PASSAR OS PARAMETROS PARA O DELETE
        query.setParameter("ISNI",ISNI);
        query.executeUpdate();
    }

    @Transactional
    public void atualizar(Autor autor)
    {
        String nomeSql="ATUALIZAR_AUTOR_BYISNI";
        Query query=em.createNamedQuery(nomeSql);

        query.setParameter("ISNI",autor.getISNI());
        query.setParameter("email",autor.getEmail());
        query.setParameter("nome",autor.getNome());
        query.setParameter("biografia",autor.getBiografia());

        query.executeUpdate();
    }

}

