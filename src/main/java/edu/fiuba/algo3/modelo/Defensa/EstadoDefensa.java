package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.List;

public interface EstadoDefensa {
    int atacar(List<Pasarela> pasarelasEnRango,int danio);

    void siguienteEstado(Defensa defensa);

    void atacar(Enemigo enemigo, int danio, int rango, Posicion posicion) throws DefensaIncompletaNoPuedeAtacar;
}
