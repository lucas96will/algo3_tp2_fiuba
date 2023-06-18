package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoVivo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoVolador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Lechuza extends Enemigo{
    public Lechuza(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(5,1,5), new MovimientoVolador(), unaPosicion);

    }

    @Override
    public void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public void moverse(List<Parcela> parcelas){
        estado.moverse(movimiento, parcelas, this, posicion); //TODO add el movimiento es raro
    }

    @Override
    public void daniarAlJugador() {
        estado.daniarAlJugador(this.toString()); // TODO add el danio al jugador es destruirle una torre
        this.estado = new EstadoEnemigoMuerto();
    }

    public void moverseEnojado(){
        //Cuando tiene 50% menos de la vida
    }
}
