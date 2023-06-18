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

    public EstadoEnemigoVivo(int unaVida, int unDanio, int unaVelocidad) {
        vida = unaVida;
        vidaInicial = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;

    }

    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual){
        for(int i = 0; i < velocidad; i++){
            movimiento.moverse(parcelas, enemigo, posActual);
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
    public void recibirAtaqueYEvolucionar(Lechuza lechuza, int unDanio, Posicion posicionAtacante) {
        //Codigo que por temas de herencia solo recibe una lechuza
        if(vida - unDanio > vidaInicial *0.5){
            recibirAtaque(lechuza, unDanio, posicionAtacante);
            lechuza.enojate(vida, danio, velocidad);
        }
        recibirAtaque(lechuza, unDanio, posicionAtacante);;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
