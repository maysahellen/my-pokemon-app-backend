package infraestructure.domain;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class ListPokemonResponse {

    @JsonbProperty("results")
    private List<PokemonResponse> results;

    public ListPokemonResponse(List<PokemonResponse> results) {
        this.results = results;
    }

    public ListPokemonResponse() {}

    public List<PokemonResponse> getResults() {
        return results;
    }

    public void setResults(List<PokemonResponse> results) {
        this.results = results;
    }
}
