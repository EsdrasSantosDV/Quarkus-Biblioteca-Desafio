package br.com.stefanini.developerup.service.feing;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.stefanini.developerup.model.Livros;

@Path("/library")
@RegisterRestClient
public interface LibraryService {

	@GET
    @Path("/livros/{livros}")
    Set<Livros> getByStream(@QueryParam("livros") Integer ISBN);




}
