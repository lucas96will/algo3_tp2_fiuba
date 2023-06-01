package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasarelaTest {
    @Test
    public void enemigoSoloSePuedeCrearEnUnaPasarela() {

        Parcela pasarela = new Pasarela(null, new Posicion(0,0));
        Parcela tierra = new Tierra();
        Parcela rocoso = new Rocoso();

        Exception exceptionTierra = assertThrows(Exception.class, () -> tierra.insertarEnemigo(Enemigo.crearHormiga(1)));
        Exception exceptionRocoso = assertThrows(Exception.class, () -> rocoso.insertarEnemigo(Enemigo.crearHormiga(1)));

        assertEquals("Solo la pasarela puede contener un enemigo", exceptionTierra.getMessage());
        assertEquals("Solo la pasarela puede contener un enemigo", exceptionRocoso.getMessage());
        assertDoesNotThrow(() -> pasarela.insertarEnemigo(Enemigo.crearHormiga(1)));

    }
}
