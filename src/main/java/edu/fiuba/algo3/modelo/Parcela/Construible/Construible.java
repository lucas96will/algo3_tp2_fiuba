package edu.fiuba.algo3.modelo.Parcela.Construible;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerTrampa;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

public abstract class Construible implements Parcela {
    protected Posicion posicion;

    public Construible() { //TODO: borrar inicializaciones que no seteen los atributos o los pongan en null.
    }

    public Construible(Posicion unaPosicion) {
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
        return null;
    }
    
    @Override
    public void construir(TrampaDeArena trampa, Posicion posicion){
        if(posicion.equals(this.posicion)) {
            Jugador.getInstance().obtenerReembolso(trampa);
            throw new ParcelaNoPuedeContenerTrampa("No se puede contruir una trampa de arena fuera de la pasarela");
        }
    }
}
