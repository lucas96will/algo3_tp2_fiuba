package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.List;

public class Arania extends Enemigo{
    public Arania(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(2,2,2), unaPosicion);
        movimiento = new MovimientoTerrestre();
    }

    public Arania(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoVivo(unaVida,unDanio,unaVelocidad));
        movimiento = new MovimientoTerrestre();
    }

    @Override
    protected void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        jugador.incrementarContadorAranias();
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public String toString() {

        return ("Araña en " +  posicion.toString());
    }


    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
    }
}
