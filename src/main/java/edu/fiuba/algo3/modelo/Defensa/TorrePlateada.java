package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Posicion;

public class TorrePlateada extends Defensa {
    public TorrePlateada(Posicion posicion) {
        super(posicion);
        this.costeEnCreditos = 20;
        this.rango = 5;
        this.danio = 2;
        this.estado = new EstadoDefensaIncompleto(2);
    }

    public TorrePlateada(Posicion posicion, EstadoDefensa unEstadoDefensa) {
        super(posicion, unEstadoDefensa);
        this.costeEnCreditos = 20;
        this.rango = 3;
        this.danio = 1;
    }
}
