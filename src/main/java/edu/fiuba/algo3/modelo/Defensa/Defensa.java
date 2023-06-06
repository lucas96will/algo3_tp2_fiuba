package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;
import java.util.ArrayList;
import java.util.List;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int rango;
    protected final Posicion posicion;
    protected int danio;
    protected EstadoDefensa estado;

    public Defensa(Posicion posicion) {
        this.posicion = posicion;
    }

    public Defensa(Posicion posicion, EstadoDefensa unEstadoDefensa) {
        this.posicion = posicion;
        this.estado = unEstadoDefensa;
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

    public int atacar(Enemigo enemigo) {
        int creditos = 0;
        if (estado.puedeAtacar()){
            return creditos + enemigo.recibirDanio(danio);
        }
        estado = estado.reconstruir();
        return 0;
    }

    public Posicion getPosicion() {
        return posicion;
    }
}
