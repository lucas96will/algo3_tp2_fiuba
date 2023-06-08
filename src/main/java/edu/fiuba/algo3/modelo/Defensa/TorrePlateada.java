package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Posicion;

public class TorrePlateada extends Defensa {


    public TorrePlateada(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, Posicion posicion) {
        super(costo, danio, rango, unEstadoDefensa, posicion);
    }
    public TorrePlateada(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa) {
        super(costo, danio,rango, unEstadoDefensa);
    }

    @Override
    public String toString() {
        return "Torre plateada";
    }
}
