package edu.fiuba.algo3.modelo.Direccion;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Arriba implements Direccion{

    public void mover(Posicion unaPosicion){
        unaPosicion.reducirFila();
    }
}
