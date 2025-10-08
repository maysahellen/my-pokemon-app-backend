package br.com.vivo.application.usecases;

import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;
import br.com.vivo.templates.ListPokemonResponseTemplate;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Given ListPokemon")
class ListPokemonTest {

    private ListPokemon listPokemon;
    private LogCaptor logCaptor;
    List<String> logs;

    @BeforeEach
    public void setup() {
        ListPokemonResponseTemplate.loadTemplates();
        listPokemon = new ListPokemon();
        logCaptor = LogCaptor.forClass(ListPokemon.class);
    }

    @Nested
    @DisplayName("When listPokemonResponseToListPokemonEntity is called")
    class WhenListPokemonResponseToListPokemonEntity {

        @Nested
        @DisplayName("And the function is successful")
        class WhenListPokemonResponseToListPokemonEntityIsSuccessful {

            ListPokemonResponse responses;
            List<ListPokemonEntity> result;

            @BeforeEach
            public void setup() {
                responses = ListPokemonResponseTemplate.gimmeValid();
                result = listPokemon.listPokemonResponseToListPokemonEntity(responses);
                logs = logCaptor.getInfoLogs();
            }

            @Test
            @DisplayName("Then return the correct name")
            void thenReturnTheCorrectName() {
                assertEquals("charmander", result.get(0).getName());
            }

            @Test
            @DisplayName("Then the log is correct")
            void getListPokemonLogIsCorrect() {
                Assertions.assertTrue(logs.contains("[ListPokemon:ListPokemonResponseToListPokemonEntity] id, name and image were extracted from response"));
            }

            @Test
            @DisplayName("The the id is correct")
            void getListPokemonIdIsCorrect() {
                assertEquals("4", result.get(0).getId());
            }

            @Test
            @DisplayName("Then the url image is correct")
            void getListPokemonUrlImageIsCorrect() {
                assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png", result.get(0).getImage());
            }
        }

        @Nested
        @DisplayName("And the params were incorrects")
        class WhenListPokemonResponseToListPokemonEntityIsIncorrect {

            List<ListPokemonEntity> entities;

            @BeforeEach
            public void setup() {
                entities = listPokemon.listPokemonResponseToListPokemonEntity(null);
                logs = logCaptor.getErrorLogs();
            }

            @Test
            @DisplayName("Then return an empty array list")
            void thenReturnAnEmptyArrayList() {
                Assertions.assertTrue(entities.isEmpty());
            }

            @Test
            @DisplayName("Then the log is correct")
            void getIdFromUrlLogIsCorrect() {
                Assertions.assertTrue((logs.contains("[ListPokemon:ListPokemonResponseToListPokemonEntity] pokemonResponse is null, returning an empty list")));
            }
        }
    }

    @Nested
    @DisplayName("When getIdFromUrl is called")
    class WhenGetIdFromUrl {

        @Nested
        @DisplayName("And the param is correct")
        class WhenGetIdFromUrlIsCorrect {

            String id;
            List<String> logs;

            @BeforeEach
            public void setup() {
                id = listPokemon.getIdFromUrl("https://pokeapi.co/api/v2/pokemon/4/");
                logs = logCaptor.getInfoLogs();
            }

            @Test
            @DisplayName("Then return the id")
            void thenReturnTheId() {
                assertEquals("4", id);
            }

            @Test
            @DisplayName("Then the log is correct")
            void getIdFromUrlLogIsCorrect() {
                Assertions.assertTrue((logs.contains("[ListPokemon:ListPokemonResponseToListPokemonEntity] id extracted: 4")));
            }
        }
    }
}