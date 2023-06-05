package edu.fiuba.algo3.modelo;

public class Posicion {
    private int fila;
    private int columna;

    public Posicion(int posFila, int posCol){
        fila = posFila;
        columna = posCol;
    }

    public boolean estaEnRango(int rango,Posicion posicion){
        int dist_vertical = Math.abs(fila - posicion.fila);
        int dist_horizontal = Math.abs(columna - posicion.columna);
        int dist = Math.max(dist_horizontal, dist_vertical);

        return Math.abs(dist) <= rango;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}