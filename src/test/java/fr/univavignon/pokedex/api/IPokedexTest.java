package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class IPokedexTest {

    private Pokedex pokedex;
    private List<Pokemon> pokemons;
    private Pokemon bulbizarre;
    private Pokemon aquali;

    @Before
    public void setUp(){

        this.pokedex = Mockito.mock(Pokedex.class);
        pokemons = new ArrayList<>();
        bulbizarre = new Pokemon(0,
                "Bulbizarre",
                126,
                126,
                90,
                613,
                64,
                4000,
                4,
                56.0);
        pokemons.add(bulbizarre);

        aquali =  new Pokemon(
                133,
                "Aquali",
                186,
                186,
                260,
                2729,
                202,
                5000,
                4,
                100.0
        );

        pokemons.add(aquali);


    }

    @Test
    public void shouldReturnSize(){

        //when
        Mockito.doReturn(this.pokemons.size()).when(this.pokedex).size();

        //then
        assertEquals(2,this.pokedex.size());
    }

    @Test
    public void shouldAddPokemon() {

        // giving
        Pokemon newPokemon = new Pokemon(
                133,
                "Aquali",
                186,
                186,
                260,
                2729,
                202,
                5000,
                4,
                100.0
        );

        // when
        Mockito.doReturn(this.pokemons.size()+1).when(this.pokedex).addPokemon(newPokemon);

        //then
        assertEquals(3,this.pokedex.addPokemon(newPokemon));
    }

    @Test
    public void canGetPokemon() throws PokedexException {

        // giving
        int aqualiIndex = 133;
        int bulbizarreIndex = 0;
        int firstInvalidIndex = 170;
        int secondInvalidIndex = -20;

        //when
        Mockito.doReturn(aquali)
                .when(this.pokedex)
                .getPokemon(aqualiIndex);
        Mockito.doReturn(bulbizarre)
                .when(this.pokedex)
                .getPokemon(bulbizarreIndex);
        Mockito.doThrow(new PokedexException("invalid given index"))
                .when(this.pokedex)
                .getPokemon(Mockito.intThat(i -> i < 0 || i > 150));

        //then
        assertEquals(aquali,this.pokedex.getPokemon(133));
        assertEquals(bulbizarre,this.pokedex.getPokemon(0));
        assertThrows(PokedexException.class, () -> this.pokedex.getPokemon(firstInvalidIndex));
        assertThrows(PokedexException.class, () -> this.pokedex.getPokemon(secondInvalidIndex));
    }

    @Test
    public void shouldGetPokemons(){

        // given
        List<Pokemon> unmodifiablePokemons = Collections.unmodifiableList(this.pokemons);

        // when
        Mockito.doReturn(unmodifiablePokemons).when(this.pokedex).getPokemons();

        // then
        // Class Name check
        assertEquals(unmodifiablePokemons.getClass(),this.pokedex.getPokemons().getClass());
        // Size check
        assertEquals(unmodifiablePokemons.size(),this.pokedex.getPokemons().size());
        // Content check
        assertEquals(unmodifiablePokemons.get(0),this.pokedex.getPokemons().get(0));
        assertEquals(unmodifiablePokemons.get(1),this.pokedex.getPokemons().get(1));
    }

    @Test
    public void shouldGetSortedPokemons(){

        // given
        PokemonComparators nameComparator = PokemonComparators.NAME;
        PokemonComparators indexComparator = PokemonComparators.INDEX;
        PokemonComparators cpComparator = PokemonComparators.CP;

        List<Pokemon> nameSortedPokemons = new ArrayList<>(this.pokemons);
        nameSortedPokemons.sort(nameComparator);

        List<Pokemon> indexSortedPokemons = new ArrayList<>(this.pokemons);
        indexSortedPokemons.sort(indexComparator);

        List<Pokemon> cpSortedPokemons = new ArrayList<>(this.pokemons);
        cpSortedPokemons.sort(cpComparator);

        List<Pokemon> expectedUnmodifiableList = Collections.unmodifiableList(new ArrayList<>());



        // when

        Mockito.doReturn(Collections.unmodifiableList(nameSortedPokemons)).when(this.pokedex).getPokemons(nameComparator);
        Mockito.doReturn(Collections.unmodifiableList(indexSortedPokemons)).when(this.pokedex).getPokemons(indexComparator);
        Mockito.doReturn(Collections.unmodifiableList(cpSortedPokemons)).when(this.pokedex).getPokemons(cpComparator);

        // then

        // Class Name check
        assertEquals(expectedUnmodifiableList.getClass(),this.pokedex.getPokemons(nameComparator).getClass());
        // Size check
        assertEquals(indexSortedPokemons.size(),this.pokedex.getPokemons(indexComparator).size());
        // Content check
        assertEquals(aquali,this.pokedex.getPokemons(nameComparator).get(0));
        assertEquals(bulbizarre,this.pokedex.getPokemons(indexComparator).get(0));
        assertEquals(bulbizarre,this.pokedex.getPokemons(cpComparator).get(0));

    }
}
