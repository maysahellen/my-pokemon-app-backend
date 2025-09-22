package br.com.vivo.application.usecases;

import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.PokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ListPokemon {

    private final Logger LOGGER;

    @Inject
    public ListPokemon(Logger logger) {
        this.LOGGER = logger;
    }

    public List<ListPokemonEntity> listPokemonResponseToListPokemonEntity (ListPokemonResponse pokemonResponse) {

        if (pokemonResponse == null) {
            LOGGER.severe("pokemonResponse is null");
            return new ArrayList<>();
        }

        String id;
        String name;
        String imageUrl;
        List<ListPokemonEntity> pokemonEntityList = new ArrayList<>();

        for(PokemonResponse pokemon : pokemonResponse.getResults()) {
            name = pokemon.getName();
            id = getIdFromUrl(pokemon.getUrl());
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";

            ListPokemonEntity listPokemonEntity = new ListPokemonEntity(name, id, imageUrl);

            pokemonEntityList.add(listPokemonEntity);
        }
        LOGGER.info("[ListPokemon:ListPokemonResponseToListPokemonEntity] id, name and image were extracted from the response successfully");
        return pokemonEntityList;
    }

    public String getIdFromUrl(String url) {
        LOGGER.info("[ListPokemon:ListPokemonResponseToListPokemonEntity] the id was extracted successfully");
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
