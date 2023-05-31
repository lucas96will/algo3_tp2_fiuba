package edu.fiuba.algo3.modelo.Defensa;

public class TorrePlateada extends Defensa {
    public TorrePlateada(int una_posicion_fila, int una_posicion_columna) {
        super(una_posicion_fila, una_posicion_columna);
        this.costeEnCreditos = 20;
        this.rango = 5;
        this.danio = 2;
        this.estado = new EstadoDefensaIncompleto(2);
    }
}
