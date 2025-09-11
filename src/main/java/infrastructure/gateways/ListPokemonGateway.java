package infrastructure.gateways;

import domain.ListPokemonResponse;

public interface ListPokemonGateway {
    ListPokemonResponse getListPokemon(String offset, String limit);
}
