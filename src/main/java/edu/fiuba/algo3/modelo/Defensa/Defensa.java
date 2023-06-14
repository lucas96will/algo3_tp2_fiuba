package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;

import java.util.List;
import java.util.stream.Collectors;

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
    
    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa) {
        this.posicion = NullPosicion.obtenerNullPosicion();
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
    }

    public boolean comprate(Recurso recurso) {
        return recurso.gastar(costeEnCreditos);
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

    public int atacar(List<Enemigo> enemigos) {
        int creditos = 0;
        List<Enemigo> enemigosEnRango = enemigos.stream().filter(e->e.estaEnRango(rango, posicion)).collect(Collectors.toList());
        for(Enemigo enemigo : enemigosEnRango){
            if (estado.puedeAtacar()) {
                Logger.getInstance().logExitoso(this + " ataco a " + enemigo);
                creditos += enemigo.recibirDanio(danio);
            }
        }
        return creditos;


        /*
        if (estado.puedeAtacar() && enemigo.estaEnRango(rango, posicion)){
            return creditos + enemigo.recibirDanio(danio);
        }
        //estado = estado.reconstruir();
        return 0;*/
    }

    /*public Posicion getPosicion() {
        return posicion;
    }*/

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
