package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class EstadoEnemigoBajoTierra implements EstadoEnemigo {

    private int cantidadMovimientos;
    private int danio;
    private int velocidad;

    public EstadoEnemigoBajoTierra(int unDanio, int unaVelocidad, int unaCantidadMovimientos) {
        danio = unDanio;
        velocidad = unaVelocidad;
        cantidadMovimientos = unaCantidadMovimientos;
    }

    @Override
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        for(int i = 0; i < velocidad; i++){
            movimiento.moverse(parcelas, enemigo, posActual);
            cantidadMovimientos++;
            actualizarVelocidad();
        }
    }

    @Override
    public void daniarAlJugador(String nombreEnemigo) {
        Jugador jugador = Jugador.getInstance();
        /*if(turno % 2 == 0){
            jugador.reducirVidaJugador(2);
        } else {
            jugador.reducirVidaJugador(5);
        }*/ //TODO Add agregar la funcionalidad del danio dependiente del numero de turno del juego
        jugador.reducirVidaJugador(danio);
        Logger.getInstance().logError(nombreEnemigo + " hizo " + danio + " de daño al jugador");
    }

    @Override
    public void recibirAtaque(Enemigo enemigo, int unDanio, Posicion posicionAtacante) {

    }


    private void actualizarVelocidad(){
        if(cantidadMovimientos > 5 && cantidadMovimientos < 11) {
            velocidad = 2;
        } else if (cantidadMovimientos >= 11) {
            velocidad = 3;
        }
    }
}
