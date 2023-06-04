package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.JsonCargador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsosTest {
    @Test
    public void caso1VerificarElFormatoValidoDelJsonEnemigos() {
        //Verificar el formato valido del JSON enemigos.
        String rutaJsonEnemigos = "../data/jsonTests/enemigos.json";
        String rutaJsonMapa = "../data/jsonTests/mapa.json";

        JsonCargador jsonCargador = new JsonCargador();

        assertTrue(jsonCargador.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso2VerificarElFormatoValidoDelJsonMapa() {
        //Verificar el formato valido del JSON MAPA.
        String rutaJsonEnemigos = "../data/jsonTests/enemigos.json";
        String rutaJsonMapa = "../data/jsonTests/mapa.json";

        JsonCargador jsonCargador = new JsonCargador();

        assertTrue(jsonCargador.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }
}