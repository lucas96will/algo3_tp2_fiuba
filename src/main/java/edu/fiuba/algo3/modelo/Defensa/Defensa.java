package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Cobrable.Cobrable;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;

import java.util.List;

public abstract class Defensa implements Cobrable, Posicionable {
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

    
    @Override
    public void comprate(Recurso recurso) throws NoSePudoComprarException {
            recurso.gastar(costeEnCreditos);
    }
    
    @Override
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
                if(enemigo.estaEnRango(rango, posicion)){ //TODO viola tda
                    estado.atacar(enemigo, danio);
                    Logger.getInstance().logExitoso(enemigo + " recibio ataque de Torre en " + posicion );
                }
            } catch (Exception e) {

            }
        }
    }

    public boolean tieneLaMismaPosicion(Posicion posicion) {
        return this.posicion.esIgual(posicion);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public boolean mismaPosicionQueDefensa(Defensa unaDefensa) {
        return posicion.equals(unaDefensa.posicion);
    }

}
