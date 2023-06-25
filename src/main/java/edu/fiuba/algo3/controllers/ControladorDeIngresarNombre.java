package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.view.BotonIngresarNombre;
import edu.fiuba.algo3.view.BotonPantallaInicio;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeIngresarNombre implements Initializable {

    @FXML private AnchorPane anchorPane;
    @FXML private TextField nombre;
    private Button boton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarBotonEmpezar();
        boton.setDisable(true);
        nombre.textProperty().addListener((obs, oldText, newText) -> {
            boton.setDisable(newText.isEmpty());
        });
    }

    public EventHandler empezar () {
        return event -> {
                Jugador jugador = Jugador.getInstance();
                jugador.actualizarEstado(100, new Recurso(100), nombre.getText());
                Juego.getInstance().cargarJugador(Jugador.getInstance());
                Juego.getInstance().iniciar();
                CargadorDeEscena.cargarScene("/fxml/juego.fxml", App.obtenerStage());
        };
    }

    private void configurarBotonEmpezar() {
        boton = BotonIngresarNombre.fijarBotonEmpezar(this);
        anchorPane.getChildren().add(boton);
    }
}
