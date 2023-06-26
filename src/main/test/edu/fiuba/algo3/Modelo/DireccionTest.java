package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Direccion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DireccionTest {

    private int fila = 1;
    private int columna = 1;
    @BeforeEach
    public void setup() {

    }
    @Test
    public void test01ArribaMueveFilaYColumnaCorrectamente() {
        Direccion direccion = new Arriba();
        int filaEsperada = 0;
        int columnaEsperada = 1;

        int filaObtenida = direccion.moverFila(fila);
        int columnaObtenida = direccion.moverColumna(columna);

        assertEquals(filaEsperada, filaObtenida);
        assertEquals(columnaEsperada, columnaObtenida);
    }

    @Test
    public void test02AbajoMueveFilaYColumnaCorrectamente() {
        Direccion direccion = new Abajo();
        int filaEsperada = 2;
        int columnaEsperada = 1;

        int filaObtenida = direccion.moverFila(fila);
        int columnaObtenida = direccion.moverColumna(columna);

        assertEquals(filaEsperada, filaObtenida);
        assertEquals(columnaEsperada, columnaObtenida);
    }

    @Test
    public void test03IzquierdaMueveFilaYColumnaCorrectamente() {
        Direccion direccion = new Izquierda();
        int filaEsperada = 1;
        int columnaEsperada = 0;

        int filaObtenida = direccion.moverFila(fila);
        int columnaObtenida = direccion.moverColumna(columna);

        assertEquals(filaEsperada, filaObtenida);
        assertEquals(columnaEsperada, columnaObtenida);
    }

    @Test
    public void test04DerechaMueveFilaYColumnaCorrectamente() {
        Direccion direccion = new Derecha();
        int filaEsperada = 1;
        int columnaEsperada = 2;

        int filaObtenida = direccion.moverFila(fila);
        int columnaObtenida = direccion.moverColumna(columna);

        assertEquals(filaEsperada, filaObtenida);
        assertEquals(columnaEsperada, columnaObtenida);
    }
}
