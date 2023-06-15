package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
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
    
    public Defensa(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, String nombre) {
        this.posicion = NullPosicion.obtenerNullPosicion();
        this.estado = unEstadoDefensa;
        this.costeEnCreditos = costo;
        this.rango = rango;
        this.danio = danio;
        this.nombre = nombre;
    }

    public void comprate(Recurso recurso) throws NoSePudoComprarException {
        try {
            recurso.gastar(costeEnCreditos);

        } catch (RecursosInsuficientesException e) {
            throw new NoSePudoComprarException();
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
            if (estado.puedeAtacar()){
                try {
                    enemigo.recibirAtaque(danio, rango, posicion);
                    Logger.getInstance().logExitoso(this + " ataco a " + enemigo );
                } catch (RuntimeException e) {

                }
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
