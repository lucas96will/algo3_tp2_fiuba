package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigo;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;

import java.util.List;

public abstract class Enemigo implements Posicionable {
    protected EstadoEnemigo estado;
    protected Posicion posicion;
    protected Movimiento movimiento;
    

    public Enemigo(EstadoEnemigo unEstado, Movimiento unMovimiento, Posicion unaPosicion) {
        posicion = unaPosicion;
        movimiento = unMovimiento;
        estado = unEstado;
    }

    public Enemigo(EstadoEnemigo unEstado, Movimiento unMovimiento) {
        posicion = null;
        movimiento = unMovimiento;
        estado = unEstado;
    }

    public void morir(){
        Jugador jugador = Jugador.getInstance();
        jugador.obtenerRecompensa(this);
        jugador.incrementarContador(this);
        Logger.getInstance().logExitoso(this + " murio.");
    }

    public boolean muerto() {
        return estado.getClass().equals(EstadoEnemigoMuerto.class); //Mal y pronto
    }

    public void mover(Posicion unaPosicion) {
        this.posicion = new Posicion(unaPosicion);
    }

    public void moverse(List<Parcela> parcelas){estado.moverse(movimiento, parcelas, this, posicion);}

    public abstract void daniarAlJugador();

    public boolean estaEnRango(int rango, Posicion unaPosicion) {
        return this.posicion.estaEnRango(rango, unaPosicion);
    }

    public void establecerVelocidad(float reduccionVelocidad) {
        estado.establecerVelocidadRestante(reduccionVelocidad);
    }

    public abstract void obtenerRecompensa(Recurso recursoJugador, int contadorMuertes);
    public abstract String nombre();
    public abstract void siguienteEstado(int vidaActual, int vidaInicial);

    public void recibirAtaque(int unDanio, int unRango, Posicion unaPosicion) {
        if(posicion.estaEnRango(unRango, unaPosicion)){
            estado.recibirAtaque(this, unDanio);
            Logger.getInstance().logExitoso(this + " recibio ataque de Torre en " + posicion);
        }
    }
}
