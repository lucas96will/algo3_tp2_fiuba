package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Pasarela implements Parcela {

    private Posicion posicion;
    private EstadoPasarela estado;
    
    public Pasarela(Posicion unaPosicion, EstadoPasarela unEstado) {
        posicion = unaPosicion;
        estado = unEstado;
    }

    public void insertarDefensa(Defensa defensa, List<Defensa> defensasJugador) throws DefensaNoSePudoConstruir {
        if(defensa.tieneLaMismaPosicion(posicion)){
            throw new DefensaNoSePudoConstruir("No se puede construir una defensa en una pasarela");
        }
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
    public Posicion obtenerPosicionFinal() {
        return estado.obtenerPosicionFinal(posicion);
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }
    
    public void establecerDireccion(Direccion unaDireccion){ estado.establecerDireccion(unaDireccion);}

    public void actualizarEstado(){
        estado = estado.actualizarEstado();
    }

    public void construir(TrampaDeArena nuevoEstado, Posicion unaPosicion){
        if(posicion.equals(unaPosicion)) { // TODO viola tda
            estado = estado.construir(nuevoEstado);
        }
    }
}
