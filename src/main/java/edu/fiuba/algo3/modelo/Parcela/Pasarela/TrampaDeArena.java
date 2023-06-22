package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Cobrable.Cobrable;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class TrampaDeArena implements EstadoPasarela, Cobrable {

    private int costeEnCreditos;
    private EstadoPasarela estadoAnterior;
    private float reduccionVelocidad;
    private int vidaUtil;

    public TrampaDeArena() {
        reduccionVelocidad = (float) 0.5;
        vidaUtil = 3;
        costeEnCreditos = 25;
    }

    public void moverEnemigo(Enemigo enemigo, Posicion unaPosicion) {
        enemigo.establecerVelocidad(reduccionVelocidad);
        estadoAnterior.moverEnemigo(enemigo, unaPosicion);
    }

    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        estadoAnterior.establecerDireccion(unaDireccion);
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion) {

    }

    public EstadoPasarela actualizarEstado() {
        if (vidaUtil == 1) {
            return estadoAnterior;
        }
        vidaUtil--;
        return this;
    }

    public Posicion obtenerPosicionFinal(Posicion posicion) {
        return new NullPosicion();
    }

    public void establecerAnterior(EstadoPasarela unEstadoPasarela) {
        estadoAnterior = unEstadoPasarela;
    }

    public EstadoPasarela construir(TrampaDeArena nuevoEstado) {
        return this;
    }

    @Override
    public void comprate(Recurso recurso) throws RecursosInsuficientesException {
            recurso.gastar(costeEnCreditos);
    }

    @Override
    public void reembolsarCreditos(Recurso recurso) {
        recurso.sumarMonedas(costeEnCreditos);
    }


    @Override
    public String toString() {
        return "Trampa arenosa";
    }
}
