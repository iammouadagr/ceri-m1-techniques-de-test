package fr.univavignon.pokedex.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TestHelloWorld {

    @Test
    public void testLogHelloWorld(){
        assertEquals("Hello World!", HelloWorld.logHelloWorld());
    }
}
