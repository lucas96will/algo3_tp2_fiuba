package edu.fiuba.algo3.modelo.Defensa;

public class TorreBlanca extends Defensa {
    public TorreBlanca(int una_posicion_fila, int una_posicion_columna) {
        super(una_posicion_fila, una_posicion_columna);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
        this.estado = new EstadoDefensaIncompleto(1);
    }

}
