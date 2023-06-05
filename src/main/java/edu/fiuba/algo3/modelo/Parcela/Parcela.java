package edu.fiuba.algo3.modelo.Parcela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

public interface Parcela {
    public boolean construirDefensa(Defensa unaDefensa);
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception;
    public void insertarDefensa(Defensa defensa) throws Exception;
}
