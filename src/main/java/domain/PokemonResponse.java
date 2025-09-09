package domain;

import jakarta.json.bind.annotation.JsonbProperty;

public class PokemonResponse {

    @JsonbProperty("name") // faz a correspondencia entre o campo do json e o atributo
    private String name;
    @JsonbProperty("url")
    private String url;

    public PokemonResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public PokemonResponse() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
