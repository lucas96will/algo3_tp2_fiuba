package edu.fiuba.algo3.modelo.Cobrable;

import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador.Recurso;

public interface Cobrable {
    void comprate(Recurso recurso) throws RecursosInsuficientesException;
    
    void reembolsarCreditos(Recurso recurso);
}
