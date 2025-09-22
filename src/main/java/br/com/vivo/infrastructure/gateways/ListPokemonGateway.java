package br.com.vivo.infrastructure.gateways;

import br.com.vivo.domain.ListPokemonResponse;

public interface ListPokemonGateway {
    ListPokemonResponse getListPokemon(String offset, String limit);
}
