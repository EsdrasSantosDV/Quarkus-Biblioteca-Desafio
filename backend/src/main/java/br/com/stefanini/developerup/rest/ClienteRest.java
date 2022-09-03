package br.com.stefanini.developerup.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.stefanini.developerup.dto.ClienteDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.stefanini.developerup.service.ClienteService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cliente")
@RequestScoped
public class ClienteRest {
    @Inject
    ClienteService service;

    @Inject
    Validator validator;

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Clientes")
    @APIResponse(responseCode = "200", description = "ClienteDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }
    
    @POST
    @Operation(summary = "Cadastar", description = "Cadastar um Cliente")
    @APIResponse(responseCode = "201", description = "ClienteDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response cadastrar(ClienteDto cliente) throws Exception {

        Set<ConstraintViolation<ClienteDto>> erros=validator.validate(cliente);
        if(erros.isEmpty())
        {
            service.cadastrar(cliente);
        }
        else
        {
            List<String> listaErros=erros.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new NotFoundException(listaErros.get(0));
        }


        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{email}")
    @Operation(
            summary = "Buscar um Cliente por email",
            description = "Buscar um Cliente por Email"
    )
    @APIResponse(
            responseCode = "200",
            description = "cliente",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = ClienteDto.class))
            })
    public Response buscarPorEmail(@PathParam("email") String email)
    {
        return Response.status(Response.Status.OK)
                .entity(service.buscar(email))
                .build();
    }


    @PUT
    @Path("/{email}")
    @Operation(
            summary = "Atualizar um cliente pelo Email",
            description = "Atualizar um Cliente pelo Email"
    )
    @APIResponse(
            responseCode = "200",
            description = "atualizar cliente",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = ClienteDto.class))
            })
    public Response atualizar(@PathParam("email") String email,ClienteDto todo)
    {
        service.atualizar(email,todo);
        return Response.status(Response.Status.OK).build();

        //PODERIA RETORANR O ENTITY
    }


    @DELETE
    @Path("/{email}")
    @Operation(
            summary = "Excluir um Cliente pelo Email",
            description = "Excluir uma Cliente pelo Email"
    )
    @APIResponse(
            responseCode = "202",
            description = "excluir cliente",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = ClienteDto.class))
            })
    public Response excluir(@PathParam("email") String email){
        service.excluir(email);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }






}
   