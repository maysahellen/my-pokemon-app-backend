package org.example.infrastructure.adapters;

import org.example.domain.ListPokemonResponse;
import org.example.domain.PokemonResponse;
import org.example.infrastructure.exception.GetListPokemonException;
import org.example.infrastructure.gateways.PokemonGateway;
import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given ListPokemon")
public class ListPokemonAdapterTest {

    @InjectMocks
    ListPokemonAdapter listPokemonAdapter;

    @Mock
    PokemonGateway pokemonGatewayMock; // Mudando para o Gateway

    @Mock
    Logger loggerMock;

    @BeforeEach
    public void beforeEach() {
        reset(pokemonGatewayMock);
        reset(loggerMock);
    }

    @Nested
    @DisplayName("When getListPokemon is called")
    class getListPokemonAdapterTest {

        @Nested
        @DisplayName("And the call of api is success")
        class apiIsSucess {

            String limit;
            String offset;
            ListPokemonResponse response;
            List<PokemonResponse> pokemonResponseList;

            @BeforeEach
            public void beforeEach() {

                offset = "0";
                limit = "1";

                PokemonResponse pokemonResponse = new PokemonResponse();
                pokemonResponse.setName("Pikachu");
                pokemonResponse.setUrl("https://pokeapi.co/api/v2/pokemon/25");

                ListPokemonResponse listPokemonResponse = new ListPokemonResponse();
                pokemonResponseList = new ArrayList<>();
                pokemonResponseList.add(pokemonResponse);
                listPokemonResponse.setResults(pokemonResponseList);

                // Mock do Gateway
                when(pokemonGatewayMock.getListPokemon(limit, offset)).thenReturn(listPokemonResponse);

                // Chamando o metodo a ser testado
                response = listPokemonAdapter.getListPokemon(limit, offset);
            }

            @Test
            @DisplayName("Then the function returns the list of pokemons")
            void getListPokemonReturnsTheListOfPokemons() {
                verify(loggerMock).info("[ListPokemonAdapter:getListPokemon] Data collected successfully");
            }

            @Test
            @DisplayName("Then the log is correct")
            void getListPokemonLogIsCorrect() {
                assertEquals(pokemonResponseList, response.getResults());
            }
        }

        @Nested
        @DisplayName("And the call of api is error")
        class apiIsError {

            String limit;
            String offset;
            String errorText;
            GetListPokemonException thrownException;

            @BeforeEach
            public void beforeEach() {
                offset = "0";
                limit = "1";
                errorText = "[ListPokemonAdapter:getListPokemon] Error trying to get pokemon data"; // mensagem que vai aparecer quando a excessao for lancada

                // falando que quando o gateway for lancado vai lancar a excessao com o texto definido
                when(pokemonGatewayMock.getListPokemon(limit, offset)).thenThrow(new GetListPokemonException(errorText));

                try{
                    listPokemonAdapter.getListPokemon(limit, offset);
                }
                catch(GetListPokemonException e){
                    thrownException = e; // coloca a excessao na variavel
                }
            }

            @Test
            @DisplayName("Then throw an exception")
            void getListPokemonExceptionThrowsAnException() {
                assertEquals(errorText, thrownException.getMessage()); // verifica se o texto definido é o mesmo da excessao lancada
            }

            @Test
            @DisplayName("Then the log is correct")
            void getListPokemonLogIsCorrect() {
                verify(loggerMock).severe("[ListPokemonAdapter:getListPokemon] Error trying to get pokemon data");
            }
        }
    }
}