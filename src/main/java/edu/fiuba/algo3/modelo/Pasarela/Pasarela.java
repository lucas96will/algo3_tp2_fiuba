package edu.fiuba.algo3.modelo.Pasarela;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;

import java.util.ArrayList;

public class Pasarela {
    public Pasarela anterior;
    public Pasarela siguiente;
    private ArrayList<Enemigo> enemigosEncima;
    public Posicion posicion;

    public Pasarela(Pasarela pasarela_anterior, Posicion unaPosicion){
        anterior = pasarela_anterior;
        enemigosEncima = new ArrayList<Enemigo>();
        posicion = unaPosicion;
    }
    public void fijarSiguiente(Pasarela pasarela_siguiente){
        siguiente = pasarela_siguiente;
    }

    public void insertarEnemigo(Enemigo enemigo) {
        enemigosEncima.add(enemigo);
    }

    public int atacarAlPrimerEnemigo(int danio) {
        for(Enemigo enemigo: enemigosEncima){
            enemigo.recibirDanio(danio);
            return danio;
        }
        return 0;
    }

    public boolean llegoAlaLargada() {
        return anterior != null;
    }

    public boolean llegoAlaMeta() {
        return siguiente != null;
    }

    public void moverEnemigos() {
        for (Enemigo enemigo : enemigosEncima) {
            enemigo.mover(this);
        }
    }

    public void eliminarEnemigo(Enemigo enemigo) {
        enemigosEncima.remove(enemigo);
    }



}
