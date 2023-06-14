package edu.fiuba.algo3.Modelo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
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
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100),"Joaquin");
        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
        assertFalse(partida.terminarPartida());
    }

}
