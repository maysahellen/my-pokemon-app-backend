package infrastructure.adapters;

import domain.ListPokemonResponse;
import infrastructure.exception.GetListPokemonException;
import infrastructure.gateways.PokemonGateway;
import infrastructure.gateways.ListPokemonGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@ApplicationScoped
public class ListPokemonAdapter implements ListPokemonGateway {

    // guarda um objeto gateway
    private final PokemonGateway pokemonGateway;
    private static final Logger LOGGER = Logger.getLogger(ListPokemonAdapter.class.getName());

    // instancia a interface gateway e coloca no atributo
    @Inject
    public ListPokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    // implementando o metodo da interface
    @Override
    public ListPokemonResponse getListPokemon(String limit, String offset) {

        try{
            LOGGER.info("[ListPokemonAdapter:getListPokemon] Getting pokemon data from api");
            ListPokemonResponse response = pokemonGateway.getListPokemon(limit, offset);
            LOGGER.info("[ListPokemonAdapter:getListPokemon] Data collected successfully"); // retorna o size da lista
            return response;
        }
        catch(Exception e){
            LOGGER.severe("[ListPokemonAdapter:getListPokemon] Error trying to get pokemon data");
            throw new GetListPokemonException(e.getMessage());
        }
    }
}
