package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;

public interface EstadoPartida {
    boolean equals(Object o);

    int hashCode();

    void terminarTurno(Mapa mapa);

    void construir(Defensa defensa, Posicion posicion, Jugador jugador, Mapa mapa);

    void insertarEnemigo(Enemigo enemigo, Mapa mapa);

    void insertarEnemigos(List<Enemigo> enemigos, Mapa mapa);

    void construirTrampa(TrampaDeArena trampa, Posicion posicion, Jugador jugador, Mapa mapa);

}