package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class EstadoEnemigoBajoTierra implements EstadoEnemigo {

    private int DANIO_TURNO_IMPAR = 5;
    private int DANIO_TURNO_PAR = 2;
    private int cantidadMovimientos;
    private int danio;
    private int velocidadRestante = 0;
    private int velocidad;

    public EstadoEnemigoBajoTierra(int unDanio, int unaVelocidad, int unaCantidadMovimientos) {
        danio = unDanio;
        velocidad = unaVelocidad;
        cantidadMovimientos = unaCantidadMovimientos;
    }

    @Override
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        actualizarVelocidad();
        velocidadRestante = velocidad;
        while(velocidadRestante > 0){
            movimiento.moverse(parcelas, enemigo, posActual);
            velocidadRestante--;
        }
        cantidadMovimientos++;
    }


    @Override
    public void daniarAlJugador(String nombreEnemigo) {
        Jugador jugador = Jugador.getInstance();
        int danio = (ContadorTurnos.obtenerContador().obtenerTurnoActual() % 2 == 0) ? DANIO_TURNO_PAR : DANIO_TURNO_IMPAR;

        jugador.reducirVidaJugador(danio);
        Logger.getInstance().logError(nombreEnemigo + " hizo " + danio + " de daño al jugador");
    }

    @Override
    public void recibirAtaque(Enemigo enemigo, int unDanio) {
    }

    @Override
    public void recibirAtaque(Lechuza lechuza, int unDanio){

    }

    @Override
    public void establecerVelocidadRestante(float reduccion) {
        if(velocidadRestante > 1){
            velocidadRestante *= reduccion;
        }
    }


    private void actualizarVelocidad(){
        if(cantidadMovimientos > 5 && cantidadMovimientos < 11) {
            velocidad = 2;
        } else if (cantidadMovimientos >= 11) {
            velocidad = 3;
        }
    }
}
