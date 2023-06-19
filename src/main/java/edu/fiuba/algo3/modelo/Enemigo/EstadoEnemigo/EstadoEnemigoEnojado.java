package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class EstadoEnemigoEnojado implements EstadoEnemigo {

    private final int vidaInicial;
    private int velocidad;
    private int danio;
    private int vida;
    private int velocidadRestante;

    public EstadoEnemigoEnojado(int unaVida, int unDanio, int unaVelocidad) {
        vida = unaVida;
        vidaInicial = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
    }

    @Override
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        velocidadRestante = velocidad;
        while(velocidadRestante > 0){
            movimiento.moverseEnojado(parcelas,enemigo, posActual);
            velocidadRestante--;
        }
        /*
        for (int i = 0; i < velocidad; i++) {
            movimiento.moverseEnojado(parcelas, enemigo, posActual);
        }*/
    }

    @Override
    public void daniarAlJugador(String nombreEnemigo) {

    }

    @Override
    public void recibirAtaque(Enemigo unEnemigo, int unDanio, Posicion posicionAtacante) {
        vida -= unDanio;
        Logger.getInstance().logExitoso(unEnemigo + " recibio ataque de Torre en " + posicionAtacante);
        if (vida <= 0) {
            unEnemigo.morir();
        }
    }

    @Override
    public void establecerVelocidadRestante(float reduccion) {
        if(velocidadRestante > 1){
            velocidadRestante *= reduccion;
        }
    }

    @Override
    public void recibirAtaqueYEvolucionar(Lechuza lechuza, int unDanio, Posicion posicionAtacante) {
        recibirAtaque(lechuza, unDanio, posicionAtacante);
    }
}
