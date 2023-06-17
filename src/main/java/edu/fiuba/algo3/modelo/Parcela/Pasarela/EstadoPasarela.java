package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public interface EstadoPasarela {
    public void insertarEnemigo (Enemigo unEnemigo, Posicion posicion);

    public boolean moverEnemigo(Enemigo enemigo, Posicion posicion);

    void establecerDireccion(Direccion unaDireccion);
}
