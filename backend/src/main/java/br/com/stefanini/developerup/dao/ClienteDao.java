package br.com.stefanini.developerup.dao;

import br.com.stefanini.developerup.model.Cliente;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@RequestScoped
@Traced
public class ClienteDao {

    //INJETAR O CONTEXTO
    @PersistenceContext
    EntityManager em;


    public List<Cliente> listar()
    {
        String nomeConsulta="CONSULTAR_CLIENTES";
        TypedQuery<Cliente> query=em.createNamedQuery(nomeConsulta,Cliente.class);
        List<Cliente> listaRetorno;
        try{
            listaRetorno=  query.getResultList();
        }catch (NoResultException e){
            listaRetorno=new ArrayList();
        }
        return listaRetorno;
    }


    @Transactional
    public void cadastrar(Cliente cliente)
    {
        String nomeSql="INSERIR_CLIENTE";
        Query query=em.createNamedQuery(nomeSql);
        query.setParameter("email",cliente.getEmail());
        query.setParameter("nome",cliente.getNome());
        query.setParameter("contato",cliente.getContato());

        query.executeUpdate();


    }
    public boolean EmailSendoUsado(String email) {
        String nameQuery = "EMAIL_SENDO_USADO";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("email", email);


        return query.getResultList().isEmpty() ? false : true;
    }

    public Cliente buscarPorEmail(String email){
        String nomeConsulta="CONSULTAR_CLIENTE_BYEMAIL";
        Cliente cliente;
        TypedQuery<Cliente> query=em.createNamedQuery(nomeConsulta,Cliente.class);
        query.setParameter("email",email);

        try{
            cliente=  query.getSingleResult();
        }catch (NoResultException e){
            cliente=null;
        }

        return cliente;

    }

    @Transactional
    public void excluirPorEmail(String email)
    {
        String nomeConsulta="EXCLUIR_CLIENTE_BYEMAIL";
        Query query=em.createNamedQuery(nomeConsulta);

        //PASSAR OS PARAMETROS PARA O DELETE
        query.setParameter("email",email);


        query.executeUpdate();
    }

    @Transactional
    public void atualizar(Cliente cliente)
    {
        String nomeSql="ATUALIZAR_CLIENTE_BYEMAIL";
        Query query=em.createNamedQuery(nomeSql);

        query.setParameter("email",cliente.getEmail());
        query.setParameter("nome",cliente.getNome());
        query.setParameter("contato",cliente.getContato());

        query.executeUpdate();
    }






}
