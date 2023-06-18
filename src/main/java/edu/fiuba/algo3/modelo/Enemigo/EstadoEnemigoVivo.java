package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class EstadoEnemigoVivo implements EstadoEnemigo {

    public EstadoEnemigoVivo(){}
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual, Posicion posAnterior){
        movimiento.moverse(parcelas, enemigo, posActual, posAnterior);
    }

    @Override
    public void daniarAlJugador(int unDanio) {
        Jugador jugador = Jugador.getInstance();
        jugador.reducirVidaJugador(unDanio);
        Logger.getInstance().logError(this + " hizo " + unDanio + " de daño al jugador");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
