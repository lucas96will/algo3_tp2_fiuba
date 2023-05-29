package edu.fiuba.algo3.modelo;

public class TorrePlateada extends Defensa {
    public TorrePlateada(int una_posicion_fila, int una_posicion_columna) {
        super(una_posicion_fila, una_posicion_columna);
        this.costeEnTurnos = 2;
        this.costeEnCreditos = 20;
    }
}
