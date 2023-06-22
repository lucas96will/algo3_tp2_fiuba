package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class EstadoEnemigoVivo implements EstadoEnemigo {

    private final int vidaInicial;
    private int velocidad;
    private int danio;
    private int vida;
    private int velocidadRestante;

    public EstadoEnemigoVivo(int unaVida, int unDanio, int unaVelocidad) {
        vida = unaVida;
        vidaInicial = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;

    }

    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual){
        velocidadRestante = velocidad;
        while(velocidadRestante > 0){
            movimiento.moverse(parcelas, enemigo, posActual);
            velocidadRestante--;
        }

    }

    @Override
    public void daniarAlJugador(String nombreEnemigo) {
        Jugador jugador = Jugador.getInstance();
        jugador.reducirVidaJugador(danio);
        Logger.getInstance().logError(nombreEnemigo + " hizo " + danio + " de daño al jugador");
    }

    @Override
    public void recibirAtaque(Enemigo unEnemigo, int unDanio, Posicion posicionAtacante) {
        vida -= unDanio;
        Logger.getInstance().logExitoso(unEnemigo + " recibio ataque de Torre en " + posicionAtacante );
        if(vida <= 0) {
            unEnemigo.morir();
        }
    }

    @Override
    public void recibirAtaque(Lechuza lechuza, int unDanio, Posicion posicionAtacante){
        vida -= unDanio;
        Logger.getInstance().logExitoso(lechuza + " recibio ataque de Torre en " + posicionAtacante );
        if(vida <= 0) {
            lechuza.morir();
        } else if(vida <= vidaInicial * 0.5 ) {
            lechuza.enojate();
        }
    }
    
    @Override
    public void establecerVelocidadRestante(float reduccion) {
        if(velocidadRestante > 1){
            velocidadRestante *= reduccion;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
