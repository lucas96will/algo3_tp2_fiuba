package edu.fiuba.algo3.modelo.Defensa;

public class TorrePlateada extends Defensa {
    public TorrePlateada(int unaPosicionFila, int unaPosicionColumna) {
        super(unaPosicionFila, unaPosicionColumna);
        this.costeEnCreditos = 20;
        this.rango = 5;
        this.danio = 2;
        this.estado = new EstadoDefensaIncompleto(2);
    }

    public TorrePlateada(int unaPosicionFila, int unaPosicionColumna, EstadoDefensa unEstadoDefensa) {
        super(unaPosicionFila, unaPosicionColumna, unEstadoDefensa);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
    }
}
