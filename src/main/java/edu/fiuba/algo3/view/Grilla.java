package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeGrilla;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Grilla extends GridPane {

    public static GridPane fijarGrilla(List<Posicionable> posicionables, EventHandler eventTierra, EventHandler<ActionEvent> eventPasarela, EventHandler<ActionEvent> eventRocoso){
        ControladorDeGrilla controladorDeGrilla = cargarControlador();
        return controladorDeGrilla.obtenerGrillaDelTerreno(posicionables, eventTierra, eventPasarela, eventRocoso);
    }

    public static GridPane fijarGrillaSuperpuestas(int filas, int columnas){
        ControladorDeGrilla controladorDeGrillaSuperpesta = cargarControlador();
        return controladorDeGrillaSuperpesta.obtenerGrillaSuperpuestas(filas,columnas);
    }

    private static ControladorDeGrilla cargarControlador() {
        ControladorDeGrilla controladorDeGrilla = new ControladorDeGrilla();
        controladorDeGrilla.initialize((App.class.getResource("/fxml/grilla.fxml")),null);
        return controladorDeGrilla;
    }
}
