package br.com.vivo.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.PokemonResponse;

public class ListPokemonResponseTemplate implements TemplateLoader {

    private static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.vivo.templates");
    }

    @Override
    public void load() {
        Fixture.of(PokemonResponse.class).addTemplate("valid", new Rule() {
            {
                add("name", "charmander");
                add("url", "https://pokeapi.co/api/v2/pokemon/4/");
            }
        });

        Fixture.of(ListPokemonResponse.class).addTemplate(VALID, new Rule() {
            {
                add("results", has(1).of(PokemonResponse.class, "valid"));
            }
        });
    }

    public static ListPokemonResponse gimmeValid() {
        return Fixture.from(ListPokemonResponse.class).gimme(VALID);
    }
}