package edu.fiuba.algo3.Modelo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    public Mapa obtenerMapaGenerico() {
        Mapa mapa = new Mapa(8);

        mapa.agregarParcelaEnPosicion(new Casilla(), new Posicion(1,1));

        for(int i = 2; i < 8; i++){
            mapa.agregarParcelaEnPosicion(new Casilla(), new Posicion(1,i));
        }
        mapa.agregarParcelaEnPosicion(new Casilla(), new Posicion(1,7));

        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {
                mapa.agregarParcelaEnPosicion(new Tierra(), new Posicion(j, k));
            }
        }
        for(int h = 1; h < 8; h++) {
            mapa.agregarParcelaEnPosicion(new Rocoso(), new Posicion(7, h));
        }

        mapa.iniciarLargada();
        return mapa;
    }

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
        Mapa mapa = obtenerMapaGenerico();
        partida.crearPartida(jugador,mapa);

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
        assertFalse(partida.terminarPartida());
    }

}
