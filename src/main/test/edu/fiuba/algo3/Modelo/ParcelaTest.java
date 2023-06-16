package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelaTest {
    @Test
    public void enemigoNoSePuedeCrearEnTierra() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 1: Enemigo no se puede crear en tierra");
        Parcela pasarela = new Pasarela(new Posicion(0,0), new Casilla());
        Parcela tierra = new Tierra(new Posicion(0,0));
        Parcela rocoso = new Rocoso(new Posicion(0,0));
        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        assertThrows(Exception.class, () -> tierra.insertarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }

    @Test
    public void enemigoNoSePuedeCrearEnRocoso() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 2: Enemigo no se puede crear en rocoso");
        Parcela pasarela = new Pasarela(new Posicion(0,0), new Casilla());
        Parcela rocoso = new Rocoso(new Posicion(0,0));
        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }

    @Test
    public void enemigoSePuedeCrearEnPasarela() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 3: Enemigo se puede crear en pasarela");
        Parcela pasarela = new Pasarela(new Posicion(0,0), new Casilla());
        Parcela tierra = new Tierra(new Posicion(0,0));
        Parcela rocoso = new Rocoso(new Posicion(0,0));
        
        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        assertThrows(Exception.class, () -> tierra.insertarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }
}
