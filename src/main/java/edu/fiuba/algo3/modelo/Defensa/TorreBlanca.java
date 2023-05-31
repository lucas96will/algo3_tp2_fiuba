package edu.fiuba.algo3.modelo.Defensa;

public class TorreBlanca extends Defensa {
    public TorreBlanca(int unaPosicionFila, int unaPosicionColumna) {
        super(unaPosicionFila, unaPosicionColumna);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
        this.estado = new EstadoDefensaIncompleto(1);
    }

    public TorreBlanca(int unaPosicionFila, int unaPosicionColumna, EstadoDefensa unEstadoDefensa) {
        super(unaPosicionFila, unaPosicionColumna, unEstadoDefensa);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
    }
}
