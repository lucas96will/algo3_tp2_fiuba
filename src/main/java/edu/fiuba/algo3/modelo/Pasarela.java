package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pasarela {
    private Pasarela anterior;
    private Pasarela siguiente;
    private ArrayList<Enemigo> enemigosEncima;

    public Pasarela(Pasarela pasarela_anterior){
        anterior = pasarela_anterior;
        enemigosEncima = new ArrayList<Enemigo>();
    }
    public void fijarSiguiente(Pasarela pasarela_siguiente){
        siguiente = pasarela_siguiente;
    }

    public void insertarEnemigo(Enemigo enemigo) {
        enemigosEncima.add(enemigo);
    }


}
