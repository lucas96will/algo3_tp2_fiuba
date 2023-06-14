package edu.fiuba.algo3.modelo.Cargador;

public interface Cargador {
    void archivoEsCorrecto(String ruta) throws RuntimeException;
    void cargarMapa(String ruta);
    void cargarEnemigos(String ruta);
}
