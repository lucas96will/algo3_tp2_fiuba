package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigo;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;

import java.util.List;

public abstract class Enemigo {
    protected EstadoEnemigo estado;
    protected Posicion posicion;
    protected Movimiento movimiento;
    

    public Enemigo(EstadoEnemigo unEstado, Movimiento unMovimiento, Posicion unaPosicion) {
        posicion = unaPosicion;
        movimiento = unMovimiento;
        estado = unEstado;
    }

    public Enemigo(EstadoEnemigo unEstado, Movimiento unMovimiento) {
        posicion = new NullPosicion();
        movimiento = unMovimiento;
        estado = unEstado;
    }

    public void recibirAtaque(int unDanio) {
        estado.recibirAtaque(this, unDanio);
    }

    public abstract void morir();

    public boolean muerto() {
        return estado.getClass().equals(EstadoEnemigoMuerto.class); //Mal y pronto
    }

    public void mover(Posicion unaPosicion) {
        this.posicion = new Posicion(unaPosicion);
    }

    public abstract void moverse(List<Parcela> parcelas);
    
    
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
}
