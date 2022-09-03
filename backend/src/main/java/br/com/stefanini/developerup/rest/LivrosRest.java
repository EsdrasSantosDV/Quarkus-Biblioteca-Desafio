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

import br.com.stefanini.developerup.dto.LivrosDto;
import br.com.stefanini.developerup.service.LivrosService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/livros")
@RequestScoped
public class LivrosRest {
	@Inject
	LivrosService service;
	@Inject
	Validator validator;

	@GET
	@Operation(summary = "Listar", description = "Retorna uma lista de Livros")
	@APIResponse(responseCode = "200", description = "LivroDto",
			content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = LivrosDto.class))})
	public Response listar()  {
		return Response.status(Response.Status.OK).entity(service.listar()).build();
	}

	@POST
	@Operation(summary = "Cadastar", description = "Cadastar um Livro")
	@APIResponse(responseCode = "201", description = "LivroDto",
			content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = LivrosDto.class))})
	public Response cadastrar(LivrosDto livro) throws Exception {

		Set<ConstraintViolation<LivrosDto>> erros=validator.validate(livro);
		if(erros.isEmpty())
		{
			service.cadastrar(livro);
		}
		else
		{
			List<String> listaErros=erros.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
			throw new NotFoundException(listaErros.get(0));
		}


		return Response.status(Response.Status.CREATED).build();
	}
//
//	@GET
//	@Path("/{ISBN}")
//	@Operation(
//			summary = "Buscar um Livro por ISBN",
//			description = "Buscar um Livro por ISBN"
//	)
//	@APIResponse(
//			responseCode = "200",
//			description = "livro",
//			content ={
//					@Content(mediaType="application/json",
//							schema = @Schema(implementation = LivrosDto.class))
//			})
//	public Response buscarPorISBN(@PathParam("ISBN") String ISBN)
//	{
//		return Response.status(Response.Status.OK)
//				.entity(service.buscar(ISBN))
//				.build();
//	}
//
//
//	@PUT
//	@Path("/{ISBN}")
//	@Operation(
//			summary = "Atualizar um Livro pelo ISBN",
//			description = "Atualizar um Livro pelo ISBN"
//	)
//	@APIResponse(
//			responseCode = "200",
//			description = "atualizar cliente",
//			content ={
//					@Content(mediaType="application/json",
//							schema = @Schema(implementation = LivrosDto.class))
//			})
//	public Response atualizar(@PathParam("ISBN") String ISBN,LivrosDto livro)
//	{
//		service.atualizar(ISBN,livro);
//		return Response.status(Response.Status.OK).build();
//		//PODERIA RETORANR O ENTITY
//	}
//
//
//	@DELETE
//	@Path("/{ISBN}")
//	@Operation(
//			summary = "Excluir um Livro pelo ISBN",
//			description = "Excluir um Livro pelo ISBN"
//	)
//	@APIResponse(
//			responseCode = "202",
//			description = "excluir Livro",
//			content ={
//					@Content(mediaType="application/json",
//							schema = @Schema(implementation = LivrosDto.class))
//			})
//	public Response excluir(@PathParam("ISBN") String ISBN){
//		service.excluir(ISBN);
//		return Response.status(Response.Status.ACCEPTED)
//				.build();
//	}
//


}