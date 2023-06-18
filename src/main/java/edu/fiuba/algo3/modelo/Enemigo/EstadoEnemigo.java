package edu.fiuba.algo3.modelo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public interface EstadoEnemigo {
    
    void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual);
    void daniarAlJugador(String s);

    void recibirAtaque(Enemigo enemigo, int unDanio);
}
