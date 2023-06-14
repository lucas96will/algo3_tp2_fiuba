package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class TorreBlanca extends Defensa {

    public TorreBlanca(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, Posicion posicion) {
        super(costo, danio, rango, unEstadoDefensa, posicion);
    }
    public TorreBlanca(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa) {
        super(costo, danio, rango, unEstadoDefensa);
    }

    @Override
    public String toString() {
        return "Torre blanca";
    }
}
