package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Excepciones.FueraDeRangoException;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;

import java.util.List;

public abstract class Enemigo {
    protected EstadoEnemigo estado;
    protected Posicion posicion;
    protected Movimiento movimiento;
    

    public Enemigo(EstadoEnemigo unEstado, Posicion unaPosicion) {
        posicion = unaPosicion;
        estado = unEstado;
    }

    public Enemigo(EstadoEnemigo unEstado) {
        posicion = NullPosicion.obtenerNullPosicion();
        estado = unEstado;
    }

    public void recibirAtaque(int unDanio,int rangoAtacante, Posicion posicionAtacante) throws FueraDeRangoException {
        if(posicion.estaEnRango(rangoAtacante, posicionAtacante)) {
            estado.recibirAtaque(this, unDanio);
            Logger.getInstance().logExitoso(this + " recibio ataque de Torre en " + posicionAtacante );
        } else {
            throw new FueraDeRangoException();
        }
    }

    abstract protected void morir();

    public boolean muerto() {
        return estado.getClass().equals(EstadoEnemigoMuerto.class); //Mal y pronto
    }

    public void mover(Posicion unaPosicion) {
        this.posicion = new Posicion(unaPosicion);
    }

    public abstract void moverse(List<Parcela> parcelas);
    
    
    public void daniarAlJugador() {
        estado.daniarAlJugador(this.toString());
        this.estado = new EstadoEnemigoMuerto();
    }

    public boolean estaEnRango(int rango, Posicion posicion) {
        return this.posicion.estaEnRango(rango, posicion);
    }

}
