package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Posicion;

public class TorreBlanca extends Defensa {
    public TorreBlanca(Posicion posicion) {
        super(posicion);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
        this.estado = new EstadoDefensaIncompleto(1);
    }

    public TorreBlanca(Posicion posicion, EstadoDefensa unEstadoDefensa) {
        super(posicion, unEstadoDefensa);
        this.costeEnCreditos = 10;
        this.rango = 3;
        this.danio = 1;
    }
}
