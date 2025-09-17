package org.example.infrastructure.gateways;

import org.example.domain.ListPokemonResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "api-pokemon-base-url") // interface para fazer requisicoes + url base
public interface PokemonGateway {

    @GET
    @Path("/pokemon") // vem depois da url base
    @Produces(MediaType.APPLICATION_JSON) // tipo de dado que vai ser retornado, vai converter o json pra ListPokemonResponse
    ListPokemonResponse getListPokemon(@QueryParam("limit") String limit, @QueryParam("offset") String offset);
}

