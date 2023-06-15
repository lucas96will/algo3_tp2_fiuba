package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.Objects;

public class EstadoPartidaGanada implements EstadoPartida{
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public void terminarTurno() {
        //no hace nada
    }

    @Override
    public void construir(Defensa defensa, Posicion posicion) {
        //puede construir
    }

    @Override
    public void insertarEnemigo(Enemigo enemigo) {
        //puede insertar enemigo
    }

    @Override
    public EstadoPartida siguienteEstado() {
        return null;
    }
}
