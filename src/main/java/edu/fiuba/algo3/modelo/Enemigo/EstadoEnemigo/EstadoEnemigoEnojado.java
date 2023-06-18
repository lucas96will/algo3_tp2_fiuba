package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class EstadoEnemigoEnojado implements EstadoEnemigo{

    private final int vidaInicial;
    private int velocidad;
    private int danio;
    private int vida;
    public EstadoEnemigoEnojado(int unaVida, int unDanio, int unaVelocidad) {
        vida = unaVida;
        vidaInicial = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
    }

    @Override
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {

    }

    @Override
    public void daniarAlJugador(String nombreEnemigo) {

    }

    @Override
    public void recibirAtaque(Enemigo enemigo, int unDanio, Posicion posicionAtacante) {

    }

    @Override
    public void recibirAtaqueYEvolucionar(Lechuza lechuza, int unDanio, Posicion posicionAtacante) {
        recibirAtaque(lechuza, unDanio, posicionAtacante);
    }
}
