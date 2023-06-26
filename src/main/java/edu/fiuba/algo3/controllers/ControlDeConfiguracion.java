package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlDeConfiguracion implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public EventHandler<ActionEvent> mostrarInformacion() {
        return event -> {
            try {
                App.getInstance().abrirInformacionDelJuego();
            } catch (URISyntaxException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en informacion");
                alert.setHeaderText("No se pudo abrir la informacion del juego");
                alert.setContentText("Ocurrio un error al intentar abrir la informacion del juego");
                alert.show();
            }
        };
    }

    public EventHandler<ActionEvent> skip() {
        return event -> {
            ControladorDeSonido.getInstance().siguienteCancion();
        };
    }

    public EventHandler<ActionEvent> anteriorCancion() {
        return event -> {
            ControladorDeSonido.getInstance().cancionAnterior();
        };
    }

    public EventHandler<ActionEvent> play() {
        return event -> {
            ControladorDeSonido.getInstance().play();
        };
    }
}
