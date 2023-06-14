package edu.fiuba.algo3.modelo.Partida;

public class EstadoPartidaSigueJugando implements EstadoPartida{
    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(this.getClass());
    }
}
