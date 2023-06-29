package edu.fiuba.algo3.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorMensaje implements Initializable {
    @FXML
    private HBox mensajeBox;
    @FXML
    private Label mensaje;
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(location);
        try {
            mensajeBox = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mensaje = (Label) mensajeBox.getChildren().get(0);
        loader.setController(this);
    }

    public HBox obtenerMensaje(String unMensaje) {
        mensaje.setText(unMensaje);
        mensaje.setTextFill(Color.RED);
        mensaje.setFont(new Font("System Bold", 25));
        mensajeBox.setTranslateX(10);
        mensajeBox.setTranslateY(40);
        mensaje.setOpacity(0);
        mensaje.setPrefWidth(500);
        return mensajeBox;
    }

    public void unirPropiedad(StringProperty propiedad) {
        propiedad.bindBidirectional(mensaje.textProperty());
    }

    public void animar() {
        FadeTransition fade = new FadeTransition();
        fade.setNode(mensaje);
        fade.setDuration(Duration.millis(400));
        fade.setCycleCount(9);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setAutoReverse(true);
        fade.play();
    }
}
