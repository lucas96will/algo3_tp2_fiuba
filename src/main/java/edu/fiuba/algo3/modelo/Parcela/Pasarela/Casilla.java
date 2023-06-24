package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Casilla implements EstadoPasarela {

    private Direccion direccion;

    public void moverEnemigo(Enemigo enemigo, Posicion unaPosicion) {
        unaPosicion.mover(direccion);
    }

    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        direccion = unaDireccion;
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion){
        
    }
    public Posicion obtenerPosicionFinal(Posicion posicion) {
        return null;
    }

    public EstadoPasarela actualizarEstado(){
        return this;
    }

    public EstadoPasarela construir(TrampaDeArena trampa){
        trampa.establecerAnterior(this);
        return trampa;
    }
}
