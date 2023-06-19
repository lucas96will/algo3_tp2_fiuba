package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Cobrable.Cobrable;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
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

    public boolean moverEnemigo(Enemigo enemigo, Posicion unaPosicion) {

        enemigo.establecerVelocidad(reduccionVelocidad);
        estadoAnterior.moverEnemigo(enemigo, unaPosicion);
        return true;
    }

    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        estadoAnterior.establecerDireccion(unaDireccion);
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion) {

    }

    public EstadoPasarela actualizarEstado() {
        if (vidaUtil == 0) {
            return estadoAnterior;
        }
        vidaUtil--;
        return this;
    }

    public Posicion orientacionCosmica(Posicion posicion) {
        return NullPosicion.obtenerNullPosicion();
    }

    public void establecerAnterior(EstadoPasarela unEstadoPasarela) {
        estadoAnterior = unEstadoPasarela;
    }

    public EstadoPasarela construir(TrampaDeArena nuevoEstado) {
        return this;
    }

    @Override
    public void comprate(Recurso recurso) throws NoSePudoComprarException {
        try {
            recurso.gastar(costeEnCreditos);
        } catch (RecursosInsuficientesException e) {
            throw new NoSePudoComprarException();
        }
    }

    @Override
    public void reembolsarCreditos(Recurso recurso) {
        recurso.sumarMonedas(costeEnCreditos);
    }

}
