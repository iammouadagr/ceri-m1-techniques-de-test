package fr.univavignon.pokedex.api;
import java.util.logging.Logger;
public class HelloWorld {

    private static final Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {
        LOGGER.info("test:" + logHelloWorld());
    }

    public static String logHelloWorld() {
        return "Hello World!";
    }

}
