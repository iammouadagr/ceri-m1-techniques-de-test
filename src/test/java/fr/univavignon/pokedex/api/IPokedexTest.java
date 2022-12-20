package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
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

        pokedex = new Pokedex();
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
        pokedex.addPokemon(bulbizarre);

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

        this.pokedex.addPokemon(aquali);

        pokemons = pokedex.getPokemons();



    }

    @Test
    public void shouldReturnSize(){

        //then
        assertEquals(2,pokemons.size());
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


        //then
        assertEquals(pokemons.size()+1 ,this.pokedex.addPokemon(newPokemon));
    }

    @Test
    public void canGetPokemon() throws PokedexException {

        // giving
        int aqualiIndex = 1;
        int bulbizarreIndex = 0;
        int firstInvalidIndex = 170;
        int secondInvalidIndex = -20;

        //then
        assertEquals(aquali,this.pokedex.getPokemon(aqualiIndex));
        assertEquals(bulbizarre,this.pokedex.getPokemon(bulbizarreIndex));
        assertEquals(aquali.getName(),this.pokedex.getPokemon(aqualiIndex).getName());
        assertEquals(bulbizarre.getAttack(),this.pokedex.getPokemon(bulbizarreIndex).getAttack());
        assertEquals(aquali.getDefense(),this.pokedex.getPokemon(aqualiIndex).getDefense());
        assertEquals(bulbizarre.getStamina(),this.pokedex.getPokemon(bulbizarreIndex).getStamina());


        assertThrows(PokedexException.class, () -> this.pokedex.getPokemon(firstInvalidIndex));
        assertThrows(PokedexException.class, () -> this.pokedex.getPokemon(secondInvalidIndex));
    }

    @Test
    public void shouldGetPokemons(){

        // given
        List<Pokemon> unmodifiablePokemons = Collections.unmodifiableList(this.pokemons);

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
