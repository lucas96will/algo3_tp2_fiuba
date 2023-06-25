package edu.fiuba.algo3.modelo.Parcela.Construible;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerTrampa;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;

public abstract class Construible implements Parcela {
    protected Posicion posicion;

    public Construible(Posicion unaPosicion) {
        this.posicion = unaPosicion;
    }

    public Posicion obtenerPosicion() {return posicion;}

    abstract public void insertarDefensa(Defensa defensa, List<Defensa> defensasJugador) throws DefensaNoSePudoConstruir;

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
