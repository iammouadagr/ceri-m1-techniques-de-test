package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;


public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;

    @Before
    public void setUp(){
        this.pokedexFactory = new PokedexFactory();

    }

    @Test
    public void canCreatePokedex(){

        //given
        IPokemonMetadataProvider pokemonMetadataProvider= Mockito.mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = Mockito.mock(IPokemonFactory.class);



        //then
        assertEquals(Pokedex.class,pokedexFactory.createPokedex(pokemonMetadataProvider,pokemonFactory).getClass());
    }
}
