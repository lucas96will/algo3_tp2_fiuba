package edu.fiuba.algo3.modelo.Direccion;

public class Abajo implements Direccion {
    @Override
    public int moverFila(int fila) {
        return fila + 1;
    }

    @Override
    public int moverColumna(int columna) {
        return columna;
    }
}
