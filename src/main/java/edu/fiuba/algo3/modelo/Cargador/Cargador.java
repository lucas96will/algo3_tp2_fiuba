package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

import java.util.List;

public interface Cargador {
    void archivoEsCorrecto(String rutaEnemigos, String rutaMapa) throws RuntimeException;
    Mapa procesarMapa(String ruta);
    List<List<Enemigo>> procesarEnemigos(String ruta);
}
