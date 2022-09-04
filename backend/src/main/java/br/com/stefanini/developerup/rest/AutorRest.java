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

import br.com.stefanini.developerup.dto.AutorDto;
import br.com.stefanini.developerup.service.AutorService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AutorRest {
	@Inject
    AutorService service;
    @Inject
    Validator validator;
    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Autores")
    @APIResponse(responseCode = "200", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }

    @POST
    @Operation(summary = "Cadastar", description = "Cadastar um Autor")
    @APIResponse(responseCode = "201", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response cadastrar(AutorDto autor) throws Exception {

        Set<ConstraintViolation<AutorDto>> erros=validator.validate(autor);
        if(erros.isEmpty())
        {
            service.cadastrar(autor);
        }
        else
        {
            List<String> listaErros=erros.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new NotFoundException(listaErros.get(0));
        }


        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{ISNI}")
    @Operation(
            summary = "Buscar um Autor por ISNI",
            description = "Buscar um Autor por ISNI"
    )
    @APIResponse(
            responseCode = "200",
            description = "autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response buscarPorISNI(@PathParam("ISNI") String ISNI)
    {
        return Response.status(Response.Status.OK)
                .entity(service.buscar(ISNI))
                .build();
    }


    @PUT
    @Path("/{ISNI}")
    @Operation(
            summary = "Atualizar um Autor pelo ISNI",
            description = "Atualizar um Atualizar pelo ISNI"
    )
    @APIResponse(
            responseCode = "200",
            description = "atualizar Autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response atualizar(@PathParam("ISNI") String ISNI,AutorDto autor)
    {
        service.atualizar(ISNI,autor);
        return Response.status(Response.Status.OK).build();
    }


    @DELETE
    @Path("/{ISNI}")
    @Operation(
            summary = "Excluir um Autor pelo ISNI",
            description = "Excluir uma Autor pelo ISNI"
    )
    @APIResponse(
            responseCode = "202",
            description = "excluir autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response excluir(@PathParam("ISNI") String ISNI){
        service.excluir(ISNI);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }



}
