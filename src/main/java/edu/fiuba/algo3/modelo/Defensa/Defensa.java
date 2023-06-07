package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    protected Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;


    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, Posicion posicion) {
        this.posicion = posicion;
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
    }
    
    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa) {
        this.posicion = null;
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
    }

    public boolean comprate(Recurso recurso) {
        return recurso.gastar(costeEnCreditos);
    }

    public void establecerEstado(EstadoDefensa nuevoEstado) {
        estado = nuevoEstado;
    }

    public void siguienteEstado() {
         estado.siguienteEstado(this);
    }

    public int atacar(Enemigo enemigo) {
        int creditos = 0;
        if (estado.puedeAtacar() && enemigo.estaEnRango(rango, posicion)){
            return creditos + enemigo.recibirDanio(danio);
        }
        //estado = estado.reconstruir();
        return 0;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public boolean tieneLaMismaPosicion(Posicion posicion) {
        return this.posicion.esIgual(posicion);
    }
    
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}
