package edu.fiuba.algo3.modelo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Hormiga extends Enemigo{
    
    public Hormiga(Posicion unaPosicion) {
        super(1, 1, 1, 1, unaPosicion);
        movimiento = new MovimientoTerrestre(1);
    }

    public Hormiga(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
                super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa);
                movimiento = new MovimientoTerrestre(1);
    }


    @Override
    protected void morir() {
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

}
