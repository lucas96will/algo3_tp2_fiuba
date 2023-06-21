package edu.fiuba.algo3.modelo.Direccion;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Derecha implements Direccion {
    @Override
    public int moverFila(int fila) {
        return fila;
    }

    @Override
    public int moverColumna(int columna) {
        return columna + 1;
    }
}
