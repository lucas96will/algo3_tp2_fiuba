package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsosTest {
    @Test
    public void caso1jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        //Verificarquejugadorempiezaconlavida ylos créditos correspondientes.
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

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        //Verificarquecadadefensatardeenconstruirse lo que dice que tarda y que recién están
        // “operativas” cuando ya se terminaron de construir.
        Partida partida = new Partida();
        partida.crearPartidaGenerica("Joaquin");

        partida.comenzar();

        Defensa torreBlanca = new TorreBlanca(1,3);
        Defensa torrePlateada = new TorrePlateada(1,6);
        partida.construir(torreBlanca, 1, 3);
        partida.construir(torrePlateada, 1, 6);

        partida.terminarTurno();

        /*Torre blanca ya terminada*/
        assertTrue(partida.construccionTerminadaEn(1,3));

        /*Torre plateada no se termino (2 turnos)*/
        assertFalse(partida.construccionTerminadaEn(1,6));

        partida.terminarTurno();

        assertTrue(partida.construccionTerminadaEn(1,6));

    }
    
}
