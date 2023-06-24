package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Pasarela implements Parcela {

    private Posicion posicion;
    private EstadoPasarela estado;
    
    public Pasarela(Posicion unaPosicion, EstadoPasarela unEstado) {
        posicion = unaPosicion;
        estado = unEstado;
    }

    public Pasarela(EstadoPasarela unEstado) {
        estado = unEstado;
        posicion = null;
    }

    public void insertarDefensa(Defensa defensa) throws Exception {
        Jugador.getInstance().obtenerReembolso(defensa);
        throw new Exception("No se puede construir una defensa en una pasarela");
    }
    public void moveElEnemigo(Enemigo enemigo, Posicion actual) {
        if (actual.equals(posicion)) {
            estado.moverEnemigo(enemigo, actual);
        }
    }

    public void insertarEnemigo(Enemigo unEnemigo) {
        estado.insertarEnemigo(unEnemigo, posicion);
    }

    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public Posicion obtenerPosicionFinal() {
        return estado.obtenerPosicionFinal(posicion);
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }

    @Override
    public boolean tieneLaMismaPosicion(Posicion... posiciones) {
        return this.posicion.esIgual(posiciones);
    }

    @Override
    public boolean estaEnRangoLateralesA(Posicion posicion) {
        return this.posicion.estaEnRangoLaterales(posicion);
    }
    
    public void establecerDireccion(Direccion unaDireccion){ estado.establecerDireccion(unaDireccion);}

    public void actualizarEstado(){
        estado = estado.actualizarEstado();
    }

    public void construir(TrampaDeArena nuevoEstado, Posicion unaPosicion){
        if(posicion.equals(unaPosicion)) {
            estado = estado.construir(nuevoEstado);
        }
    }
}
