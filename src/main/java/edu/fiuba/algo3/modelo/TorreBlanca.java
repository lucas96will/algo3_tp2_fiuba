package edu.fiuba.algo3.modelo;

public class TorreBlanca extends Defensa {
    public TorreBlanca(int una_posicion_fila, int una_posicion_columna) {
        super(una_posicion_fila, una_posicion_columna);
        this.costeEnCreditos = 10;
        this.costeEnTurnos = 1;
    }



}
