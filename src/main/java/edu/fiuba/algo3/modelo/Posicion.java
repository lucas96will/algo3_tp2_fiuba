package edu.fiuba.algo3.modelo;

public class Posicion {
    public int fila;
    public int columna;

    public Posicion(int posFila, int posCol){
        fila = posFila;
        columna = posCol;
    }

    public boolean estaEnRango(int rango,Posicion posicion){
        int dist_vertical = fila - posicion.fila;
        int dist_horizontal = columna - posicion.columna;
        int dist = dist_horizontal + dist_vertical;

        return Math.abs(dist) <= rango;
    }
}
