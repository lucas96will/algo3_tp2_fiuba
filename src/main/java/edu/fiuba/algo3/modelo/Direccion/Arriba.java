package edu.fiuba.algo3.modelo.Direccion;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Arriba implements Direccion {
    @Override
    public void moverDireccion(Posicion posicion) {
        posicion.moverArriba();
    }
}
