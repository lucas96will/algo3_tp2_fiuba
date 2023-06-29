package edu.fiuba.algo3.modelo.Parcela;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;

import java.util.List;

public interface Parcela extends Posicionable {
    void insertarEnemigo(Enemigo unEnemigo) throws Exception; // TODO: lanzar una runtimeException propia para el usuario
    void insertarDefensa(Defensa defensa, List<Defensa> defensasJugador) throws DefensaNoSePudoConstruir;
    void moveElEnemigo(Enemigo enemigo, Posicion actual);
    Posicion obtenerPosicionFinal();
    Posicion obtenerPosicion();

    void construir(TrampaDeArena trampa, Posicion posicion);

    void actualizarEstado();
}
