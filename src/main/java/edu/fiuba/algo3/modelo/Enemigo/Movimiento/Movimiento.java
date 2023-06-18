package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public interface Movimiento {
    void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual);
    void moverseEnojado(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual);
}
