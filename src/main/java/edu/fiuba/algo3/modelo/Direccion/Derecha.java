package edu.fiuba.algo3.modelo.Direccion;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Derecha implements Direccion {
    public void mover(Posicion unaPosicion){
        unaPosicion.aumentarColumna();
    }
}
