package edu.fiuba.algo3.modelo.Parcela.Construible;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.NullTorre;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerTrampa;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;

public abstract class Construible implements Parcela {
    protected Posicion posicion;
    protected Defensa defensa;

    public Construible() {
        this.defensa = new NullTorre();
    }

    public Construible(Posicion unaPosicion) {
        this.defensa = new NullTorre();
        this.posicion = unaPosicion;
    }

    public Posicion obtenerPosicion() {return posicion;}

    abstract public void insertarDefensa(Defensa defensa) throws Exception;

    @Override
    public boolean tieneLaMismaPosicion(Posicion... posiciones) {
        return this.posicion.esIgual(posiciones);
    }

    @Override
    public boolean estaEnRangoLateralesA(Posicion posicion) {
        return this.posicion.estaEnRangoLaterales(posicion);
    }

    @Override
    public Posicion obtenerPosicionFinal() {
        return new NullPosicion();
    }
    
    @Override
    public void construir(TrampaDeArena trampa, Posicion posicion){
        if(posicion.equals(this.posicion)) {
            Jugador.getInstance().obtenerReembolso(trampa);
            throw new ParcelaNoPuedeContenerTrampa("No se puede contruir una trampa de arena fuera de la pasarela");
        }
    }
}
