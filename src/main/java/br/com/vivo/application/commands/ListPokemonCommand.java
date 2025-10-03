package br.com.vivo.application.commands;

import br.com.vivo.application.usecases.ListPokemon;
import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;
import br.com.vivo.infrastructure.adapters.ListPokemonAdapter;
import br.com.vivo.infrastructure.exception.GetListPokemonException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class ListPokemonCommand {

    private final ListPokemon listPokemon;
    private final ListPokemonAdapter listPokemonAdapter;
    public static final String limit = "30";
    public static final String offset = "0";
    private static final Logger log = LoggerFactory.getLogger(ListPokemonCommand.class);

    @Inject
    public ListPokemonCommand(ListPokemon listPokemon, ListPokemonAdapter listPokemonAdapter) {
        this.listPokemon = listPokemon;
        this.listPokemonAdapter = listPokemonAdapter;
    }

    // metodo que faz o gateway usando o adapter e extrai a url e nome com a usecase
    public List<ListPokemonEntity> execute() {
        try {
            ListPokemonResponse response = listPokemonAdapter.getListPokemon(limit, offset);
            List<ListPokemonEntity> pokemons = listPokemon.listPokemonResponseToListPokemonEntity(response);
            log.info("[ListPokemonCommand:execute] response: {}", pokemons);
            return pokemons;
        }
        catch (Exception e) {
            log.error("[ListPokemonCommand:execute] error: {}", e.getMessage());
            throw new GetListPokemonException(e.getMessage());
        }
    }
}
