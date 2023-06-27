package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoVivo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoVolador;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoVoladorEnojado;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Lechuza extends Enemigo{
    private static final int RECOMPENSA = 10;

    public Lechuza(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(5,1,5), new MovimientoVolador(), unaPosicion);
    }
    public Lechuza(){
        super(new EstadoEnemigoVivo(5,1,5), new MovimientoVolador());
    }

    @Override
    public void daniarAlJugador() {
        Jugador.getInstance().eliminarPrimeraTorre();
        this.estado = new EstadoEnemigoMuerto();
    }

    @Override
    public void recibirAtaque(int unDanio, int unRango, Posicion unaPosicion){
        estado.recibirAtaque(this, unDanio);
    }

    
    @Override 
    public String toString() {
        return ("Lechuza en " + posicion.toString());
    }
    
    @Override
    public void establecerVelocidad(float reduccionVelocidad) {
        
    }

    @Override
    public void obtenerRecompensa(Recurso recursoJugador, int contadorMuertes) {
        recursoJugador.sumarMonedas(RECOMPENSA);
    }

    @Override
    public String nombre() {
        return ("Lechuza");
    }

    @Override
    public void siguienteEstado(int vidaActual, int vidaInicial) {
        if(vidaActual <= 0) {
            estado = new EstadoEnemigoMuerto();
        } else if(vidaActual <= vidaInicial * 0.5 ) {
            movimiento = new MovimientoVoladorEnojado();
        }
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }

}
