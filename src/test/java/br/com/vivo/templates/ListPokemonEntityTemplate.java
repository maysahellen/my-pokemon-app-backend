package br.com.vivo.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vivo.domain.entities.ListPokemonEntity;

import java.util.Collections;
import java.util.List;

public class ListPokemonEntityTemplate implements TemplateLoader {

    private static final String VALID = "valid";

    public static void loadTemplates() {
        FixtureFactoryLoader.loadTemplates("br.com.vivo.templates");
    }

    @Override
    public void load() {
        Fixture.of(ListPokemonEntity.class).addTemplate(VALID, new Rule() {
            {
                add("id", "1");
                add("name", "bulbasaur");
                add("image", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
            }
        });

        Fixture.of(ListPokemonEntity.class).addTemplate("list", new Rule() {
            {
                add("entities", Collections.singletonList( // Collections.singletonList cria uma lista imutável com um item
                        Fixture.from(ListPokemonEntity.class).gimme(VALID)
                ));
            }
        });
    }

    public static List<ListPokemonEntity> gimmeValid() {
        return Collections.singletonList(Fixture.from(ListPokemonEntity.class).gimme(VALID));
    }
}