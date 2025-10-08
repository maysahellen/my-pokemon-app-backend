package br.com.vivo.application.commands;

import br.com.vivo.application.usecases.ListPokemon;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

class ListPokemonCommandTest {

    @InjectMocks
    private ListPokemonCommand listPokemonCommand;

    private LogCaptor logCaptor;
    List<String> logs;

    @BeforeEach
    void setUp() {
        logCaptor = LogCaptor.forClass(ListPokemon.class);
    }

    @Nested
    @DisplayName("When execute is called")
    class WhenExecuteIsCalled {

        @Nested
        @DisplayName("And the call is successful")
        class AndTheCallIsSuccessful {

            @Test
            @DisplayName("Then returns a list of PokemonEntity")
            void thenReturnsListOfPokemonEntity() {

            }

            @Test
            @DisplayName("Then the log is correct")
            void thenReturnsLog() {

            }
        }

        @Nested
        @DisplayName("And the call is an error")
        class AndTheCallIsAnError {

        }

        @Test
        @DisplayName("Then throw an exception")
        void thenThrowAnException() {

        }

        @Test
        @DisplayName("Then the log is correct")
        void thenReturnsLog() {

        }
    }


}