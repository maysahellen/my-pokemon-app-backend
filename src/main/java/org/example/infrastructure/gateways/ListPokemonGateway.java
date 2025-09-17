package org.example.infrastructure.gateways;

import org.example.domain.ListPokemonResponse;

public interface ListPokemonGateway {
    ListPokemonResponse getListPokemon(String offset, String limit);
}
