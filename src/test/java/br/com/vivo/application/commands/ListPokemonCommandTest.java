package br.com.vivo.application.commands;

import br.com.vivo.application.usecases.ListPokemon;
import br.com.vivo.domain.ListPokemonResponse;
import br.com.vivo.domain.entities.ListPokemonEntity;
import br.com.vivo.infrastructure.adapters.ListPokemonAdapter;
import br.com.vivo.infrastructure.exception.GetListPokemonException;
import br.com.vivo.templates.ListPokemonEntityTemplate;
import br.com.vivo.templates.ListPokemonResponseTemplate;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given ListPokemonCommand")
class ListPokemonCommandTest {

    @InjectMocks
    private ListPokemonCommand listPokemonCommand;

    @Mock
    ListPokemonAdapter listPokemonAdapter;

    @Mock
    ListPokemon listPokemon;

    private LogCaptor logCaptor;
    List<String> logs;

    @BeforeEach
    void setUp() {
        ListPokemonResponseTemplate.loadTemplates();
        ListPokemonEntityTemplate.loadTemplates();
        reset(listPokemonAdapter, listPokemon);
        logCaptor = LogCaptor.forClass(ListPokemonCommand.class);
    }

    @Nested
    @DisplayName("When execute is called")
    class WhenExecuteIsCalled {

        @Nested
        @DisplayName("And the call is successful")
        class AndTheCallIsSuccessful {

            ListPokemonResponse response;
            List<ListPokemonEntity> listEntity;
            List<ListPokemonEntity> resultCommand;

            @BeforeEach
            void setUp() {

                // mock da response do adapter
                response = ListPokemonResponseTemplate.gimmeValid();
                when(listPokemonAdapter.getListPokemon("30", "0")).thenReturn(response);

                // mock da resposta da usecase
                listEntity = ListPokemonEntityTemplate.gimmeValid();
                when(listPokemon.listPokemonResponseToListPokemonEntity(response)).thenReturn(listEntity);

                // chamada da função a ser testada
                resultCommand = listPokemonCommand.execute();
                logs = logCaptor.getInfoLogs();
            }

            @Test
            @DisplayName("Then returns a list of PokemonEntity")
            void thenReturnsListOfPokemonEntity() {
                Assertions.assertEquals(resultCommand, listEntity);
            }

            @Test
            @DisplayName("Then the log is correct")
            void thenReturnsLog() {
                Assertions.assertTrue(logs.contains("[ListPokemonCommand:execute] success transforming into entity"));
            }
        }

        @Nested
        @DisplayName("And the call is an error")
        class AndTheCallIsAnError {

            String errorText;
            GetListPokemonException thrownException;
            List<String> logs;

            @BeforeEach
            void setUp() {
                errorText = "[ListPokemonCommand:execute] error trying to transform to entity";

                when(listPokemonCommand.execute()).thenThrow(new GetListPokemonException(errorText));

                try{
                    listPokemonCommand.execute();
                }
                catch(GetListPokemonException e){
                    thrownException = e;
                }

                logs = logCaptor.getErrorLogs();
            }

            @Test
            @DisplayName("Then throw an exception")
            void thenThrowAnException() {
                assertEquals(errorText, thrownException.getMessage());
            }

            @Test
            @DisplayName("Then the log is correct")
            void thenReturnsLog() {
                Assertions.assertTrue(logs.contains("[ListPokemonCommand:execute] error trying to transform to entity"));
            }
        }
    }
}