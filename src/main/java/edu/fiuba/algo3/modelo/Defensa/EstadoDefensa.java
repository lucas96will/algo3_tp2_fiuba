package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import java.util.List;

public interface EstadoDefensa {
    public int atacar(List<Pasarela> pasarelasEnRango,int danio);


    public void siguienteEstado(Defensa defensa);
}
