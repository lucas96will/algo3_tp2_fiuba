package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class JuegoFacade {
    private JsonCargador archivoLoader;
    private JuegoControlador juegoControlador;

    public JuegoFacade() {
        archivoLoader = new JsonCargador();
        juegoControlador = new JuegoControlador();
    }

    public boolean cargarJuegoConJson(String jsonEnemigos,String jsonMapa){
        if(!archivoLoader.archivoEsCorrecto(jsonEnemigos, jsonMapa)){
            return false;
        }
        List<Enemigo> listaEnemigos = archivoLoader.procesarEnemigos(jsonEnemigos);
        Parcela[][] matrizMapa = archivoLoader.procesarMapa(jsonMapa);

        juegoControlador.correrPartida(listaEnemigos, matrizMapa);
        return true;
    }
}

