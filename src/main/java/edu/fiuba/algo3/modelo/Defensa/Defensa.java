package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos;

import java.util.ArrayList;
import java.util.List;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    private Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;
    protected List<Pasarela> pasarelasEnRango;


    public Defensa(int unaPosicionFila, int unaPosicionColumna) {
        posicion = new Posicion(unaPosicionFila, unaPosicionColumna);
        pasarelasEnRango = new ArrayList<>();
    }

    public Defensa(int unaPosicionFila, int unaPosicionColumna, EstadoDefensa unEstadoDefensa) {
        posicion = new Posicion(unaPosicionFila, unaPosicionColumna);
        pasarelasEnRango = new ArrayList<>();
        estado = unEstadoDefensa;
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

    public boolean comprate(Recursos recursos) {
        return recursos.gastar(costeEnCreditos);
    }

    public void establecerEstado(EstadoDefensa nuevoEstado) {
        estado = nuevoEstado;
    }

    public void siguienteEstado() {
         estado.siguienteEstado(this);
    }

    public int atacar() {
        return estado.atacar(pasarelasEnRango, danio);
    }

    public Posicion getPosicion() {
        return posicion;
    }
}
