package br.com.vivo.resources;

import br.com.vivo.application.commands.ListPokemonCommand;
import br.com.vivo.domain.entities.ListPokemonEntity;
import br.com.vivo.infrastructure.exception.GetListPokemonException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/pokemon")
public class PokemonResource {

    private final ListPokemonCommand command;
    private static final Logger log = LoggerFactory.getLogger(PokemonResource.class);

    @Inject
    public PokemonResource(ListPokemonCommand command) {
        this.command = command;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPokemon() {
        try {
            List<ListPokemonEntity> commandResponse = command.execute();
            log.info("[PokemonResource:getPokemon] Command response: {}", commandResponse);
            return Response.status(Response.Status.OK).entity(commandResponse).build();
        }
        catch (GetListPokemonException e) {
            log.error("[PokemonResource:getPokemon] error: {}", e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
