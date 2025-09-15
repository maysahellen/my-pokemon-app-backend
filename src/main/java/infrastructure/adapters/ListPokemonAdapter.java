package infrastructure.adapters;

import domain.ListPokemonResponse;
import infrastructure.exception.GetListPokemonException;
import infrastructure.gateways.PokemonGateway;
import infrastructure.gateways.ListPokemonGateway;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

public class ListPokemonAdapter implements ListPokemonGateway {

    // guarda um objeto gateway
    private final PokemonGateway pokemonGateway;

    // instancia a interface gateway e coloca no atributo
    @Inject
    public ListPokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    // implementando o metodo da interface
    @Override
    public ListPokemonResponse getListPokemon(String limit, String offset) {

        try{
            LOGGER.info("[ListPokemonAdapter:getListPokemon] Getting pokemon data from the api");
            ListPokemonResponse response = pokemonGateway.getListPokemon(limit, offset);
            return response;
        }
        catch(Exception e){
            LOGGER.error("[ListPokemonAdapter:getListPokemon] Error getting pokemon data");
            throw new GetListPokemonException(e.getMessage());
        }
    }
}
