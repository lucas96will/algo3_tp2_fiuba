package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaCompleto;
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
        enemigosEncima = new ArrayList<>();
        posicion = unaPosicion;
    }

    public boolean construirDefensa(Defensa defensa) { return false;}
    public void fijarSiguiente(Pasarela pasarelaSiguiente){
        siguiente = pasarelaSiguiente;
    }

    public void insertarEnemigo(Enemigo enemigo) {
        enemigosEncima.add(enemigo);
    }

    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        throw new Exception("Solo la tierra puede contener una defensa");
    }

    public int atacarAlPrimerEnemigo(int danio) {
        if(enemigosEncima.size() > 0) {
            return enemigosEncima.get(0).recibirDanio(danio, this);
        }

        return -1;
    }

    public boolean noLlegoAlaLargada() {
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
    }

    public void eliminarEnemigo(Enemigo enemigo) {
        enemigosEncima.remove(0);
    }


    public boolean sinEnemigos() {
        return enemigosEncima.size() == 0;
    }

    public int atacarConEstado(EstadoDefensaCompleto estado, int danio) {
        return estado.atacarEnemigos(this, enemigosEncima, danio);
    }

    public Pasarela getAnterior() {
        return anterior;
    }

    public Pasarela getSiguiente() {
        return siguiente;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
