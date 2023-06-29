package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Torre extends Defensa{
    public Torre(int costo, int danio, int rango, EstadoDefensa unEstadoDefensa, Posicion posicion, String nombre) {
        super(costo, danio, rango, unEstadoDefensa, posicion, nombre);
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }
}
