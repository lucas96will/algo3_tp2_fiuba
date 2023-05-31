package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;

public interface EstadoDefensa {
    public int atacarPasarela(Pasarela pasarela, Posicion posicion, int rango, int danio);


    public void siguienteEstado(Defensa defensa);
}
