package br.com.vivo.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vivo.domain.PokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;

public class ListPokemonEntityTemplate implements TemplateLoader {

    private static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.vivo.templates");
    }

    @Override
    public void load() {
        Fixture.of(PokemonResponse.class).addTemplate("valid", new Rule() {
            {
                add("id", "1");
                add("name", "bulbasaur");
                add("image", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
            }
        });
    }

    public static ListPokemonEntity gimmeValid() {
        return Fixture.from(ListPokemonEntity.class).gimme(VALID);
    }
}
