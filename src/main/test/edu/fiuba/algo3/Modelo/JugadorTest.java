package edu.fiuba.algo3.Modelo;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Direccion.Derecha;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    public Mapa obtenerMapaGenerico() {
        Mapa mapa = new Mapa();
        Pasarela pasarela = new Pasarela(new Posicion(1,1), new Largada());
        pasarela.establecerDireccion(new Derecha());
        mapa.agregarParcela(pasarela);

        for(int i = 2; i < 7; i++){
            pasarela = new Pasarela(new Posicion(1,i), new Casilla());
            pasarela.establecerDireccion(new Derecha());
            mapa.agregarParcela(pasarela);
        }
        pasarela = new Pasarela(new Posicion(1,7), new Meta());
        pasarela.establecerDireccion(new Derecha());
        mapa.agregarParcela(pasarela);

        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {

                mapa.agregarParcela(new Tierra(new Posicion(j, k)));
            }
        }
        for(int h = 1; h < 8; h++) {
            mapa.agregarParcela(new Rocoso(new Posicion(7, h)));
        }

        return mapa;
    }

    @Test
    public void jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO jugador empieza con vida y creditos correspondientes.");

        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100),"Joaquin");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);
        partida.terminarTurno();
        assertEquals(10, jugador.obtenerVidaJugador());
        assertEquals(100, jugador.valorCreditos());
        assertFalse(jugador.muerto());
    }


    @Test
    public void jugadorConCreditosInsuficientesAlComprarAlgoLanzaExcepcionoRecursosInsuficientesException() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(1),"Joaquin");
        Defensa torreBlanca = (new DefensaFactory())
                .obtenerDefensa("Blanca", new Posicion(1,1));

        assertThrows(RecursosInsuficientesException.class, () -> jugador.comprar(torreBlanca));
    }
}
