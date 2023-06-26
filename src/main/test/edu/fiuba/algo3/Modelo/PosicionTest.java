package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Direccion.*;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PosicionTest {

    @Test
    public void test01DosPosicionesConMismaFilaYColumnaSonIguales() {
        Posicion pos1 = new Posicion(1, 1);
        Posicion pos2 = new Posicion(1, 1);

        assertEquals(pos1, pos2);
    }
    @Test
    public void test02PosicionSeMueveCorrectamenteALaDireccionIndicada() {
        Posicion posicion = new Posicion(2, 2);
        Direccion derecha = new Derecha();
        Direccion izquierda = new Izquierda();
        Direccion arriba = new Arriba();
        Direccion abajo = new Abajo();

        posicion.mover(derecha);
        assertEquals(posicion, new Posicion(2, 3));

        posicion.mover(izquierda);
        assertEquals(posicion, new Posicion(2, 2));

        posicion.mover(arriba);
        assertEquals(posicion, new Posicion(1, 2));

        posicion.mover(abajo);
        assertEquals(posicion, new Posicion(2, 2));

    }

    @Test
    public void test03PosicionDevuelveDireccionHorizontalCorrecta() {
        Posicion posicion1 = new Posicion(1,1);
        Posicion posicion2 = new Posicion(1, 2);
        Direccion direccionEsperada = new Derecha();
        Direccion direccionObtenida = posicion1.obtenerDireccionHorizontalHacia(posicion2);

        assertTrue(direccionEsperada.getClass() == direccionObtenida.getClass());
    }

    @Test
    public void test04PosicionDevuelveDireccionVerticalCorrecta() {
        Posicion posicion1 = new Posicion(1,1);
        Posicion posicion2 = new Posicion(2, 1);
        Direccion direccionEsperada = new Abajo();
        Direccion direccionObtenida = posicion1.obtenerDireccionVerticalHacia(posicion2);

        assertTrue(direccionEsperada.getClass() == direccionObtenida.getClass());
    }
}
