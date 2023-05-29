package edu.fiuba.algo3.modelo;

public class Defensa {
    private int costeEnCreditos;
    private int CosteEnTurnos;
    private int posicion_fila;
    private int posicion_columna;

    public Defensa(int una_posicion_fila, int una_posicion_columna){
        posicion_columna = una_posicion_columna;
        posicion_fila = una_posicion_fila;
    }

    public boolean comprate(Recursos recursos){
        return recursos.gastar(costeEnCreditos);
    }
}
