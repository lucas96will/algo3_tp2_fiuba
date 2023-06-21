package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigo;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Excepciones.FueraDeRangoException;
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

    public void recibirAtaque(int unDanio,int rangoAtacante, Posicion posicionAtacante) throws FueraDeRangoException {
        if(posicion.estaEnRango(rangoAtacante, posicionAtacante)) {
            estado.recibirAtaque(this, unDanio, posicionAtacante);
        } else {
            throw new FueraDeRangoException();
        }
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

    public boolean estaEnRango(int rango, Posicion posicion) {
        return this.posicion.estaEnRango(rango, posicion);
    }

    public void establecerVelocidad(float reduccionVelocidad) {
        estado.establecerVelocidadRestante(reduccionVelocidad);
    }
}
