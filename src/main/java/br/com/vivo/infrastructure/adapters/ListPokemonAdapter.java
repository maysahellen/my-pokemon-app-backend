package br.com.vivo.infrastructure.adapters;

import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.infrastructure.exception.GetListPokemonException;
import br.com.vivo.infrastructure.gateways.PokemonGateway;
import br.com.vivo.infrastructure.gateways.ListPokemonGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ListPokemonAdapter implements ListPokemonGateway {

    // guarda um objeto gateway
    private final PokemonGateway pokemonGateway;
    private static final Logger log = LoggerFactory.getLogger(ListPokemonAdapter.class);

    // instancia a interface gateway e coloca no atributo
    @Inject
    public ListPokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    // implementando o metodo da interface
    @Override
    public ListPokemonResponse getListPokemon(String limit, String offset) {

        try{
            ListPokemonResponse response = pokemonGateway.getListPokemon(limit, offset);
            log.info("[ListPokemonAdapter:getListPokemon] Data collected successfully");
            return response;
        }
        catch(Exception e){
            log.error("[ListPokemonAdapter:getListPokemon] Error trying to get pokemon data");
            throw new GetListPokemonException(e.getMessage());
        }
    }
}
