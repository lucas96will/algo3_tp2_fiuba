package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensa.TorrePlateada;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
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
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);

        partida.comenzar();

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
        assertFalse(partida.terminarPartida());
    }

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        //Verificarquecadadefensatardeenconstruirse lo que dice que tarda y que recién están
        // “operativas” cuando ya se terminaron de construir.
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);

        partida.comenzar();

        Defensa torreBlanca = new TorreBlanca(1,3);
        Defensa torrePlateada = new TorrePlateada(1,6);
        partida.construir(torreBlanca);
        partida.construir(torrePlateada);

        partida.terminarTurno();

        /*Torre blanca ya terminada*/
        assertTrue(partida.construccionTerminadaEn(1,3));

        /*Torre plateada no se termino (2 turnos)*/
        assertFalse(partida.construccionTerminadaEn(1,6));

        partida.terminarTurno();

        assertTrue(partida.construccionTerminadaEn(1,6));

    }

    @Test
    public void caso3defensaSeConstruyeSoloSiElJugadorTieneLosCreditosNecesarios() {

        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 5);
        Defensa torreBlanca2 = new TorreBlanca(1, 6);
        partida.construir(torreBlanca1);
        partida.construir(torreBlanca2);

        assertTrue(partida.hayConstruccionEn(1,5));
        assertFalse(partida.hayConstruccionEn(1,6));

    }

    @Test
    public void caso4DefensaSoloSePuedeConstruirSobreTierra() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 5);
        Defensa torreBlanca2 = new TorreBlanca(2, 5);
        partida.construir(torreBlanca1);
        partida.construir(torreBlanca2); //construyo sobre rocoso

        assertTrue(partida.hayConstruccionEn(1,5));
        assertFalse(partida.hayConstruccionEn(2,5));
    }

    @Test
    public void caso5DefensasAtacanDentroDelRangoEsperado() {
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        Enemigo hormiga = Enemigo.crearHormiga(1);
        partida.insertarEnemigo(hormiga);

        partida.terminarTurno();

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos()); //hormiga no llego al final :D
        assertTrue(partida.hayConstruccionEn(1,1));

    }
}
