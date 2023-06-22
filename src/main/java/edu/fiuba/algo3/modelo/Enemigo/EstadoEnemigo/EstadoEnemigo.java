package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public interface EstadoEnemigo {
    
    void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual);
    void daniarAlJugador(String nombreEnemigo);
    void recibirAtaque(Enemigo enemigo, int unDanio);
    void recibirAtaque(Lechuza lechuza, int unDanio);

    void establecerVelocidadRestante(float reduccionVelocidad);
}
