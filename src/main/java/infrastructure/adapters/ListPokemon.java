package infrastructure.adapters;

import domain.ListPokemonResponse;
import infrastructure.adapters.exception.GetListPokemonException;
import infrastructure.gateways.Gateway;
import infrastructure.gateways.ListPokemonGateway;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Logger;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

public class ListPokemon implements ListPokemonGateway {

    // guarda um objeto gateway
    private final Gateway gateway;

    // instancia a interface gateway e coloca no atributo
    @Inject
    public ListPokemon(@RestClient Gateway gateway) {
        this.gateway = gateway;
    }

    // implementando o metodo da interface
    @Override
    public ListPokemonResponse getListPokemon(String limit, String offset) {

        try{
            LOGGER.info("[ListPokemonAdapter:getListPokemon] Getting pokemon data from the api");
            return gateway.getListPokemon(limit, offset);
        }
        catch(Exception e){
            LOGGER.error("[ListPokemonAdapter:getListPokemon] Error getting pokemon data");
            throw new GetListPokemonException(e.getMessage());
        }
    }
}
