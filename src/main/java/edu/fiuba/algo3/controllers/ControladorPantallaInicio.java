package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.view.BotonPantallaInicio;
import edu.fiuba.algo3.view.Configuracion;
import edu.fiuba.algo3.view.PantallaIngresarNombre;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControladorPantallaInicio implements Initializable {

    @FXML private StackPane stackPane;
    @FXML private ImageView configuracion;
    private VBox opcionesConfiguracion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuracion.setOnMouseClicked(configuracion());
        configurarBotonJugar();
        configurarConfiguracion();
    }

    public EventHandler<ActionEvent> jugar(){
        return event -> {
            try {
                String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
                String rutaJsonMapa = "data/jsonTests/mapa.json";
                Juego.getInstance().cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
                new PantallaIngresarNombre(App.getInstance(), App.obtenerStage());
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        };
    }

    public EventHandler<MouseEvent> configuracion(){
        return event -> {
            opcionesConfiguracion.setVisible(!opcionesConfiguracion.isVisible());
        };
    }

    private void configurarBotonJugar() {
        Button boton = BotonPantallaInicio.fijarBotonInicio(this);
        stackPane.getChildren().add(boton);
    }

    private void configurarConfiguracion() {
        opcionesConfiguracion = Configuracion.fijarConfiguracion();
        opcionesConfiguracion.setVisible(false);
        opcionesConfiguracion.setTranslateY(-220);
        opcionesConfiguracion.setTranslateX(520);
        stackPane.getChildren().add(opcionesConfiguracion);
    }
}
