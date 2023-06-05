package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;
import java.util.ArrayList;
import java.util.List;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    private final Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;
    protected List<Pasarela> pasarelasEnRango;

    public Defensa(Posicion posicion) {
        this.posicion = posicion;
        pasarelasEnRango = new ArrayList<>();
    }

    public Defensa(Posicion posicion, EstadoDefensa unEstadoDefensa) {
        this.posicion = posicion;
        this.pasarelasEnRango = new ArrayList<>();
        this.estado = unEstadoDefensa;
    }
    public void establecerPasarelasEnRango(Meta meta){
        Pasarela pasarelaActual = meta;
        do{
            if(posicion.estaEnRango(rango, pasarelaActual.posicion)){
                pasarelasEnRango.add(pasarelaActual);
            }
            pasarelaActual = pasarelaActual.anterior;
        }while(pasarelaActual.noLlegoAlaLargada());
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

    public int atacar() {
        int creditos = 0;
        if (estado.puedeAtacar()){
            for (Pasarela pasarela : pasarelasEnRango) {
                creditos = creditos + pasarela.atacarConEstado(new EstadoDefensaCompleto(), danio);
            }
            return creditos;
        }
        estado = estado.reconstruir();
        return 0;
    }

    public Posicion getPosicion() {
        return posicion;
    }
}
