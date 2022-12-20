package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;
    private PokemonMetadata bulbizarre;
    private PokemonMetadata aquali;


    @Before
    public void setUp(){

        this.metadataProvider = new PokemonMetadataProvider();
        this.bulbizarre = new PokemonMetadata(
                0,
                "Bulbizarre",
                126,
                126,
                90
        );
        this.aquali = new PokemonMetadata(
                133,
                "Aquali",
                186,
                168,
                260);


    }

    @Test
    public void shouldGetPokemonMetadata() throws PokedexException {

        // given
        int bulbizarreIndex = 0;
        int aqualiIndex = 133;
        int firstInvalidIndex = -4;
        int secondInvalidIndex = 160;



        // then
        assertEquals(this.bulbizarre.getIndex(),metadataProvider.getPokemonMetadata(bulbizarreIndex).getIndex());
        assertEquals(this.aquali.getDefense(),metadataProvider.getPokemonMetadata(aqualiIndex).getDefense());
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(firstInvalidIndex));
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(secondInvalidIndex));


    }
}

