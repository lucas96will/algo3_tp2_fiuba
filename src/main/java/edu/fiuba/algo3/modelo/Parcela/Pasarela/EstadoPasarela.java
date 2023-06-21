package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

public interface EstadoPasarela {
    void insertarEnemigo (Enemigo unEnemigo, Posicion posicion);

    boolean moverEnemigo(Enemigo enemigo, Posicion posicion);

    void establecerDireccion(Direccion unaDireccion);
    Posicion orientacionCosmica(Posicion posicion);

    EstadoPasarela actualizarEstado();

    EstadoPasarela construir(TrampaDeArena nuevoEstado);
}
