package br.com.vivo.application.usecases;

import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.PokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ListPokemon {

    private static final Logger log = LoggerFactory.getLogger(ListPokemon.class);

    public List<ListPokemonEntity> listPokemonResponseToListPokemonEntity (ListPokemonResponse pokemonResponse) {

        if (pokemonResponse == null) {
            log.error("[ListPokemon:ListPokemonResponseToListPokemonEntity] pokemonResponse is null, returning an empty list");
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

            ListPokemonEntity listPokemonEntity = new ListPokemonEntity(id, name, imageUrl);

            pokemonEntityList.add(listPokemonEntity);
        }
        log.info("[ListPokemon:ListPokemonResponseToListPokemonEntity] id, name and image were extracted from response");
        return pokemonEntityList;
    }

    public String getIdFromUrl(String url) {

        String id;

        // remove / do final
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        // pega a string do ultimo indice
        String[] urlDivided = url.split("/");
        id = urlDivided[urlDivided.length - 1];
        log.info("[ListPokemon:ListPokemonResponseToListPokemonEntity] id extracted: {}", id);
        return id;
    }
}
