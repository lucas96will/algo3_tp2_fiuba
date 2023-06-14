package edu.fiuba.algo3.modelo.Partida;

public class EstadoPartidaPerdida implements EstadoPartida{
    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(this.getClass());
    }


}
