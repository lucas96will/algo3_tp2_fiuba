package edu.fiuba.algo3.modelo.Direccion;

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
