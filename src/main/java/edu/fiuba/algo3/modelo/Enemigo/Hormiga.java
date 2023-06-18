package edu.fiuba.algo3.modelo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoVivo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.List;

public class Hormiga extends Enemigo{
    
    public Hormiga(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(1,1,1), new MovimientoTerrestre(), unaPosicion);
    }

    public Hormiga(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoVivo(unaVida,unDanio,unaVelocidad), new MovimientoTerrestre());
    }


    @Override
    public void morir() {
        Jugador jugador = Jugador.getInstance();
        jugador.obtenerRecompensa(this);
        jugador.incrementarContadorHormigas();
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public String toString() {

        return ("Hormiga en " +  posicion.toString());
    }
    
    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
    }

    @Override
    public void daniarAlJugador() {
        estado.daniarAlJugador(this.toString());
        this.estado = new EstadoEnemigoMuerto();
    }
}
