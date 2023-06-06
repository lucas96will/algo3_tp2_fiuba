package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Posicion;

public abstract class Construible implements Parcela {
    protected Posicion posicion;
    protected Defensa defensa;

    public Construible(Posicion unaPosicion) {
        this.defensa = null;
        this.posicion = unaPosicion;
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        throw new Exception("Solo la pasarela puede contener un enemigo");
    }

    abstract public void insertarDefensa(Defensa defensa) throws Exception;


    public boolean tieneConstruccion() {
        return (this.defensa != null);
    }
}
