package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerEnemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerTrampa;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Meta implements EstadoPasarela {

    private Direccion direccion;

    public void moverEnemigo(Enemigo enemigo, Posicion posicion) {
        enemigo.daniarAlJugador();
    }
    
    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        direccion = unaDireccion;
    }
    
    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion){
        
    }
    public Posicion obtenerPosicionFinal(Posicion posicion) {
        return posicion;
    }

    public EstadoPasarela actualizarEstado(){
        return this;
    }
    @Override
    public EstadoPasarela construir(TrampaDeArena nuevoEstado){
        Jugador.getInstance().obtenerReembolso(new TrampaDeArena());
        throw new ParcelaNoPuedeContenerTrampa("No se puede contruir una trampa de arena en la meta");
    }
}
