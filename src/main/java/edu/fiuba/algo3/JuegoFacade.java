package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa;

import java.util.List;

public class JuegoFacade {
    private CargadorJson archivoLoader;
    private JuegoControlador juegoControlador;

    public JuegoFacade() {
        archivoLoader = new CargadorJson();
        juegoControlador = new JuegoControlador();
    }

    public boolean cargarConJson(String jsonEnemigos,String jsonMapa){
        if(!archivoLoader.archivoEsCorrecto(jsonEnemigos, jsonMapa)){
            return false;
        }
        List<List<Enemigo>> turnos = archivoLoader.procesarEnemigos(jsonEnemigos);
        Mapa mapa = archivoLoader.procesarMapa(jsonMapa);

        juegoControlador.correrPartida(turnos, mapa);
        return true;
    }


}

