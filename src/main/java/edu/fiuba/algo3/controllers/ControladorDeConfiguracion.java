package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeConfiguracion implements Initializable {
    @FXML
    private VBox opcionesConfiguracion;
    @FXML
    private Button siguiente;
    @FXML
    private Button play;
    @FXML
    private Button anterior;
    @FXML
    private Button masInformacion;
    @FXML
    private Slider volumenMusica;
    @FXML
    private Slider volumenEfectos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(url);
        try {
            opcionesConfiguracion = loader.load();
            play = (Button) opcionesConfiguracion.lookup("#play");
            anterior = (Button) opcionesConfiguracion.lookup("#anterior");
            siguiente = (Button) opcionesConfiguracion.lookup("#siguiente");
            masInformacion = (Button) opcionesConfiguracion.lookup("#masInformacion");
            volumenMusica = (Slider) opcionesConfiguracion.lookup("#volumenMusica");
            volumenEfectos = (Slider) opcionesConfiguracion.lookup("#volumenEfectos");
            volumenEfectos.setValue(ControladorDeSonido.getInstance().obtenerVolumen());
            volumenMusica.setValue(ControladorDeSonido.getInstance().obtenerVolumen());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader.setController(this);
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

    private EventHandler<MouseEvent> modificarVolumenMusica() {
        return event -> {
            Slider slider = (Slider) event.getSource();
            ControladorDeSonido.getInstance().modificarVolumenMusica(slider.getValue());
        };
    }

    private EventHandler<MouseEvent> modificarVolumenEfectos() {
        return event -> {
            Slider slider = (Slider) event.getSource();
            ControladorDeSonido.getInstance().modificarVolumenEfecto(slider.getValue());
        };
    }

    public VBox obtenerConfiguracion() {
        masInformacion.setOnAction(mostrarInformacion());
        anterior.setOnAction(anteriorCancion());
        siguiente.setOnAction(skip());
        play.setOnAction(play());
        volumenMusica.setOnMouseReleased(modificarVolumenMusica());
        volumenEfectos.setOnMouseReleased(modificarVolumenEfectos());

        return opcionesConfiguracion;
    }
}
