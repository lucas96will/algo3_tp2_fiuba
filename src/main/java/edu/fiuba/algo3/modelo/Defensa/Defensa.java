package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoComprarException;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    protected Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;
    protected String nombre;


    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, Posicion posicion, String nombre) {
        this.posicion = posicion;
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
        this.nombre = nombre;
    }
    
    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, String nombre) {
        this.posicion = NullPosicion.obtenerNullPosicion();
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
        this.nombre = nombre;
    }

    public void comprate(Recurso recurso) throws DefensaNoSePudoComprarException {
        try {
            recurso.gastar(costeEnCreditos);

        } catch (RecursosInsuficientesException e) {
            throw new DefensaNoSePudoComprarException();
        }
    }

    public void reembolsarCreditos(Recurso recurso) {
        recurso.sumarMonedas(costeEnCreditos);
    }

    public void establecerEstado(EstadoDefensa nuevoEstado) {
        estado = nuevoEstado;
    }

    public void siguienteEstado() {
         estado.siguienteEstado(this);
    }


    public void atacar(List<Enemigo> enemigos) {
        for(Enemigo enemigo : enemigos) {
            try {
                estado.atacar(enemigo, danio, rango, posicion);
            } catch (Exception e) {

            }
        }
    }

    public boolean tieneLaMismaPosicion(Posicion posicion) {
        return this.posicion.esIgual(posicion);
    }
    
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
