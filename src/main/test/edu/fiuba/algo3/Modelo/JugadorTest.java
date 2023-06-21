package edu.fiuba.algo3.Modelo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    public Mapa obtenerMapaGenerico() {
        Mapa mapa = new Mapa();

        mapa.agregarParcelaEnPosicion(new Pasarela(new Largada()), new Posicion(1,1));

        for(int i = 2; i < 7; i++){
            mapa.agregarParcelaEnPosicion(new Pasarela(new Casilla()), new Posicion(1,i));
        }
        mapa.agregarParcelaEnPosicion(new Pasarela(new Meta()), new Posicion(1,7));

        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {
                mapa.agregarParcelaEnPosicion(new Tierra(), new Posicion(j, k));
            }
        }
        for(int h = 1; h < 8; h++) {
            mapa.agregarParcelaEnPosicion(new Rocoso(), new Posicion(7, h));
        }
        return mapa;
    }

    @Test
    public void jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100),"Joaquin");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);
        partida.terminarTurno();
        assertTrue(jugador.estaIntacto());
        assertFalse(jugador.muerto());
    }

}
