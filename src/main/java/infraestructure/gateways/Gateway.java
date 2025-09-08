package infraestructure.gateways;

import infraestructure.domain.ListPokemonResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://pokeapi.co") // interface para fazer requisicoes + url base
@Path("/api/v2/pokemon") // vem depois da url base
@Produces(MediaType.APPLICATION_JSON) // tipo de dado que vai ser retornado, vai converter o json pra ListPokemonResponse
public interface Gateway {

    @GET
    ListPokemonResponse getListPokemon(@QueryParam("limit") String limit, @QueryParam("offset") String offset);
}

