package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasarelaTest {
    @Test
    public void enemigoSoloSePuedeCrearEnUnaPasarela() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Pasarela test 1: Enemigo solo se puede crear en una pasarela");
        Parcela pasarela = new Casilla(new Posicion(0,0));
        Parcela tierra = new Tierra(new Posicion(0,0));
        Parcela rocoso = new Rocoso(new Posicion(0,0));
        
        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        Exception exceptionTierra = assertThrows(Exception.class, () -> tierra.insertarEnemigo(hormiga));
        Exception exceptionRocoso = assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertEquals("Solo la pasarela puede contener un enemigo", exceptionTierra.getMessage());
        assertEquals("Solo la pasarela puede contener un enemigo", exceptionRocoso.getMessage());
        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }
}
