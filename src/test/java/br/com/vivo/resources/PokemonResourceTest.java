package br.com.vivo.resources;

import br.com.vivo.application.commands.ListPokemonCommand;
import br.com.vivo.domain.entities.ListPokemonEntity;
import br.com.vivo.infrastructure.exception.GetListPokemonException;
import br.com.vivo.templates.ListPokemonEntityTemplate;
import jakarta.ws.rs.core.Response;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given PokemonResource")
class PokemonResourceTest {

    @InjectMocks
    PokemonResource pokemonResource;

    @Mock
    ListPokemonCommand listPokemonCommand;

    private LogCaptor logCaptor;

    @BeforeEach
    void setUp() {
        ListPokemonEntityTemplate.loadTemplates();
        reset(listPokemonCommand);
        logCaptor = LogCaptor.forClass(PokemonResource.class);
    }

    @Nested
    @DisplayName("When getPokemon is called")
    class WhenGetPokemonIsCalled {

        @Nested
        @DisplayName("And the call is successful")
        class AndTheCallIsSuccessful {

            List<ListPokemonEntity> mockResult;
            Response response;
            List<String> logs;

            @BeforeEach
            void setUp() {
                mockResult = ListPokemonEntityTemplate.gimmeValid();
                when(listPokemonCommand.execute()).thenReturn(mockResult);
                response = pokemonResource.getPokemon();
                logs = logCaptor.getInfoLogs();
            }

            @Test
            @DisplayName("Then response is 200")
            void thenResponseIs200Ok() {
                assertEquals(200, response.getStatus());
            }

            @Test
            @DisplayName("Then the log is correct")
            void thenTheLogIsCorrect() {
                assertTrue(logs.contains("[PokemonResource:getPokemon] Command response: " + response.getEntity().toString()));            }
        }

        @Nested
        @DisplayName("And throw GetListPokemonException")
        class AndThrowGetListPokemonException {

            String errorText;
            GetListPokemonException thrownException;
            List<String> logs;
            Response response;
            String expectedLog;

            @BeforeEach
            void setUp() {

                errorText = "[PokemonResource:getPokemon] error: ";

                when(listPokemonCommand.execute()).thenThrow(new GetListPokemonException(errorText));

                try {
                    response = pokemonResource.getPokemon();
                }
                catch (GetListPokemonException e) {
                    thrownException = e;
                }

                logs = logCaptor.getErrorLogs();
                expectedLog = "[PokemonResource:getPokemon] error: " + errorText;

            }

            @Test
            @DisplayName("Then result status is 400")
            void allPokemonsBadRequestTest() {
                assertEquals(400, response.getStatus());
            }

            @Test
            @DisplayName("Then the log is correct")
            void getListPokemonLogIsCorrect() {
                assertTrue(logs.contains(expectedLog));
            }
        }

        @Nested
        @DisplayName("And throw Exception")
        class AndThrowException {

            String errorText;
            List<String> logs;
            Response response;
            String expectedLog;


            @BeforeEach
            void setUp() {
                errorText = "Erro inesperado";
                expectedLog = "[PokemonResource:getPokemon] error: " + errorText;

                when(listPokemonCommand.execute()).thenThrow(new RuntimeException(errorText));

                response = pokemonResource.getPokemon();
                logs = logCaptor.getErrorLogs();
            }

            @Test
            @DisplayName("Then result status is 500")
            void allPokemonsBadRequestTest() {
                assertEquals(500, response.getStatus());
            }

            @Test
            @DisplayName("Then the log is correct")
            void getGenericExceptionLogIsCorrect() {
                assertTrue(logs.contains(expectedLog));
            }
        }
    }
}