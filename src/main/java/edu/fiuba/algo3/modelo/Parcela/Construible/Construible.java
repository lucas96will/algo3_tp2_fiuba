package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

public abstract class Construible implements Parcela {
    protected Defensa defensa;

    public Construible() {
        this.defensa = null;
    }

    public boolean construirDefensa(Defensa unaDefensa){
        if (defensa == null){
            defensa = unaDefensa;
            return true;
        }
        return false;
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
