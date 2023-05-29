package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Partida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsosTest {
    @Test
    public void jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        /*
        Mapa mapa = new Mapa(7);
        mapa.crearMapaGenerico(); // Línea recta con tierra construible en todas sus posiciones abajo.

        Jugador jugador = Jugador.crearJugadorBase("Joaquin"); //100 creditos y 10 puntos de vida

        assertEquals();
        Discutir sobre que modelo implementar
        */
        //-------------------------------------------------------
        Partida partida = new Partida();
        partida.crearPartidaGenerica("Joaquin");

        partida.comenzar();

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
        assertFalse(partida.terminarPartida());
    }
}
