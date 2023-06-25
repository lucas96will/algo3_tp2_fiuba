package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.view.BotonPantallaInicio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.net.URL;
import java.util.ResourceBundle;


public class ControladorPantallaInicio implements Initializable {

    @FXML private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBotonJugar();
    }

    public EventHandler<ActionEvent> jugar(){
        return event -> {
            try {
                String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
                String rutaJsonMapa = "data/jsonTests/mapa.json";
                Juego.getInstance().cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
                CargadorDeEscena.cargarScene("/fxml/juego.fxml", App.obtenerStage());
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        };
    }

    private void configurarBotonJugar() {
        Button boton = BotonPantallaInicio.fijarBotonInicio(this);
        stackPane.getChildren().add(boton);
    }
}
