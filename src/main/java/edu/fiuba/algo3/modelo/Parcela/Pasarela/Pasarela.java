package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;

import java.util.List;
import java.util.stream.Collectors;

public class Pasarela implements Parcela {

    private Posicion posicion;
    private EstadoPasarela estado;
    
    public Pasarela(Posicion unaPosicion, EstadoPasarela unEstado) {
        posicion = unaPosicion;
        estado = unEstado;
    }

    public Pasarela(EstadoPasarela unEstado) {
        estado = unEstado;
        posicion = NullPosicion.obtenerNullPosicion();
    }

    public void insertarDefensa(Defensa defensa) throws Exception {
        throw new Exception("No se puede construir una defensa en una pasarela");
    }
/*
    public boolean moveElEnemigo(Enemigo enemigo) {
        estado.moverEnemigo(enemigo, posicion);
        return true;
    }*/

    public boolean moveElEnemigo(Enemigo enemigo, Posicion actual) {
        if (actual.equals(posicion)) {
            estado.moverEnemigo(enemigo, actual);
            return true;
        }
        return false;
    }

    public void insertarEnemigo(Enemigo unEnemigo) {
        estado.insertarEnemigo(unEnemigo, posicion);
    }

    public boolean esExtremo(List<Parcela> pasarelas) {
        return this.posicion.cantidadDePasarelasAlrededor(pasarelas.stream().filter(p->p.getClass().equals(this.getClass())).collect(Collectors.toList())) <= 1;
    }

    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean tieneLaMismaPosicion(Posicion... posiciones) {
        return this.posicion.esIgual(posiciones);
    }

    @Override
    public boolean estaEnRangoLateralesA(Posicion posicion) {
        return this.posicion.estaEnRangoLaterales(posicion);
    }

    public void establecerEstado(EstadoPasarela nuevoEstado){
        estado = nuevoEstado;
    }
    
    public void establecerDireccion(Direccion unaDireccion){ estado.establecerDireccion(unaDireccion);}
}
