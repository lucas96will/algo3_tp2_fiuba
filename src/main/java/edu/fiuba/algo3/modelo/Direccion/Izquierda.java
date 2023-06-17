package edu.fiuba.algo3.modelo.Direccion;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Izquierda implements Direccion {
    public void mover(Posicion unaPosicion){
        unaPosicion.reducirColumna();
    }
}
