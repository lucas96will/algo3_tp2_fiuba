package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;
import java.util.Objects;

public class EstadoPartidaPerdida implements EstadoPartida{
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
    public void terminarTurno(Mapa mapa) {
        throw new RuntimeException("No se puede terminar turno, la partida esta perdida");
    }

    @Override
    public void construir(Defensa defensa, Posicion posicion, Jugador jugador, Mapa mapa) {
        throw new RuntimeException("No se puede construir, la partida esta perdida");
    }

    @Override
    public void insertarEnemigo(Enemigo enemigo, Mapa mapa){
        throw new RuntimeException("No se puede insertar enemigo, la partida esta perdida");
    }

    @Override
    public void insertarEnemigos(List<Enemigo> enemigos, Mapa mapa){
        throw new RuntimeException("No se puede insertar enemigo, la partida esta perdida");
    }

    @Override
    public void construirTrampa(TrampaDeArena trampa, Posicion posicion, Jugador jugador, Mapa mapa) {
        throw new RuntimeException("No se puede comprar una Trampa, la partida esta perdida");
    }

    @Override
    public EstadoPartida siguienteEstado(Mapa mapa, Jugador jugador) {
        return new EstadoPartidaPerdida();
    }


}
