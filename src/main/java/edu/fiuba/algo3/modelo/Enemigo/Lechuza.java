package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Lechuza extends Enemigo{
    public Lechuza(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
        movimiento = new MovimientoVolador();
    }

    @Override
    protected void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }
    public void moverse(List<Parcela> parcelas){

    }

    public void moverseEnojado(){
        //Cuando tiene 50% menos de la vida

    }
}
