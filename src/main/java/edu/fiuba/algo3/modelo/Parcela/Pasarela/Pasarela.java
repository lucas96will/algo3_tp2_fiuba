package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Posicion;

import java.util.ArrayList;
import java.util.Iterator;

public class Pasarela implements Parcela {
    public Pasarela anterior;
    public Pasarela siguiente;
    protected ArrayList<Enemigo> enemigosEncima;
    public Posicion posicion;

    public Pasarela(Pasarela pasarela_anterior, Posicion unaPosicion){
        anterior = pasarela_anterior;
        enemigosEncima = new ArrayList<Enemigo>();
        posicion = unaPosicion;
    }

    public boolean construirDefensa(Defensa defensa) { return false;}
    public void fijarSiguiente(Pasarela pasarela_siguiente){
        siguiente = pasarela_siguiente;
    }

    public void insertarEnemigo(Enemigo enemigo) {
        enemigosEncima.add(enemigo);
    }

    public int atacarAlPrimerEnemigo(int danio) {
        if(enemigosEncima.size() > 0) {
            return enemigosEncima.get(0).recibirDanio(danio, this);
        }

        return -1;
    }

    public boolean llegoAlaLargada() {
        return anterior != null;
    }

    public boolean llegoAlaMeta() {
        return siguiente != null;
    }

    public void moverEnemigos() {
        Iterator<Enemigo> iterador = enemigosEncima.iterator();
        while(iterador.hasNext()) {
            iterador.next().mover(this);
            iterador.remove();
        }
        /*enemigosEncima.clear();*/
    }

    public void eliminarEnemigo(Enemigo enemigo) {
        enemigosEncima.remove(0);
    }



}
