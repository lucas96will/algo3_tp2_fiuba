package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoVivo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.List;
import java.util.Random;

public class Arania extends Enemigo{
    private static final int RECOMPENSA_MAX = 10;
    private static final int RECOMPENSA_MIN = 1;

    public Arania(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(2,2,2), new MovimientoTerrestre(), unaPosicion);
    }

    public Arania(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoVivo(unaVida,unDanio,unaVelocidad), new MovimientoTerrestre());
    }

    @Override
    public String toString() {
        return ("Araña en " +  posicion.toString());
    }

    @Override
    public String nombre() {
        return ("Araña");
    }

    @Override
    public void siguienteEstado(int vidaActual, int vidaInicial) {
        if(vidaActual <= 0){
            estado = new EstadoEnemigoMuerto();
        }
    }


    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
    }

    @Override
    public void daniarAlJugador() {
        estado.daniarAlJugador(this.toString());
        this.estado = new EstadoEnemigoMuerto();
    }

    @Override
    public void obtenerRecompensa(Recurso recursoJugador, int contadorMuertes) {
        recursoJugador.sumarMonedas( new Random().nextInt(RECOMPENSA_MAX) + RECOMPENSA_MIN);
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }
}
