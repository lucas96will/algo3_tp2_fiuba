package edu.fiuba.algo3.modelo.Cobrable;

import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Jugador.Recurso;

public interface Cobrable {
    public void comprate(Recurso recurso) throws NoSePudoComprarException;
    
    public void reembolsarCreditos(Recurso recurso);
}
