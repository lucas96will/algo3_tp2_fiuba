package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerTrampa;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Largada implements EstadoPasarela {

    private Direccion direccion;

    public boolean moverEnemigo(Enemigo enemigo, Posicion unaPosicion) {
        direccion.moverDireccion(unaPosicion);
        return true;
    }

    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        direccion = unaDireccion;
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion){
        unEnemigo.mover(posicion);
    }
    public Posicion orientacionCosmica(Posicion posicion) {
        return new NullPosicion();
    }

    @Override
    public EstadoPasarela actualizarEstado(){
        return this;
    }

    @Override
    public EstadoPasarela construir(TrampaDeArena nuevoEstado){
        Jugador.getInstance().obtenerReembolso(new TrampaDeArena());
        throw new ParcelaNoPuedeContenerTrampa("No se puede contruir una trampa de arena en la largada");
    }

}
