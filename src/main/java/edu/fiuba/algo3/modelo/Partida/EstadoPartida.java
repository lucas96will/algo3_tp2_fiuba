package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public interface EstadoPartida {
    boolean equals(Object o);

    int hashCode();

    void terminarTurno();

    void construir(Defensa defensa, Posicion posicion);

    void insertarEnemigo(Enemigo enemigo);

    EstadoPartida siguienteEstado();
}