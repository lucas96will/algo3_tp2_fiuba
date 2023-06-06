package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.CargadorJson;
import edu.fiuba.algo3.JuegoFacade;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Recurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsosTest {
    private final String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
    private final String rutaJsonMapa = "data/jsonTests/mapa.json";

    @Test
    public void caso13VerificarElFormatoValidoDelJsonEnemigos() {
        //Verificar el formato valido del JSON enemigos.
        // algo3_tp2_fiuba\src\main\test\edu\fiuba\algo3\entrega_2
            //algo3_tp2_fiuba
            //├── src
            //│   └── main
            //│        └── test
            //│             └── edu
            //│                 └── fiuba
            //│                     └── algo3
            //│                         └── entrega_2
            //│                             └── CasosDeUso,java
            //│
            //└── data
            //│    └──jsonTests
            //│         └──enemigos.json


        CargadorJson cargadorJson = new CargadorJson();

        assertTrue(cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso14VerificarElFormatoValidoDelJsonMapa() {
        //Verificar el formato valido del JSON MAPA.

        CargadorJson cargadorJson = new CargadorJson();
        assertTrue(cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso15unidadesCargadasAlMapaSonCorrectas() {

        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);

        Jugador jugador = new Jugador(new Recurso(100), 20, "#Singleton");
        juego.cargarJugador(jugador);

        juego.iniciar();

        for(int i = 0; i < 50; i++) { // Juego termina, danio causado al jugador = 22
            juego.terminarTurno();
        }

        EstadoPartida estadoPartida = juego.estado();
        assertTrue(estadoPartida.perdio());
    }

}