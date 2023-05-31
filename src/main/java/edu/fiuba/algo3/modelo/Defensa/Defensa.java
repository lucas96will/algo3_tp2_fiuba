package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    public Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;
    

    public Defensa(int una_posicion_fila, int una_posicion_columna) {
        posicion = new Posicion(una_posicion_fila, una_posicion_columna);
    }

    public boolean comprate(Recursos recursos) {
        return recursos.gastar(costeEnCreditos);
    }
    
    public int atacarPasarela(Pasarela pasarela){
        
        return estado.atacarPasarela(pasarela, posicion, rango, danio);
    }

    public void establecerEstado(EstadoDefensa nuevoEstado) {
        estado = nuevoEstado;
    }

    public void siguienteEstado() {
        estado.siguienteEstado(this);
    }
}
