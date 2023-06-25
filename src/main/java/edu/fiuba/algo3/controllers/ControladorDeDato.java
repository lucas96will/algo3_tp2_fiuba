package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeDato implements Initializable {

    @FXML
    private Label dato;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(location);
        try {
            dato = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader.setController(this);
    }

    public Label obtenerDato(String informacion) {
        dato.setText(informacion);
        dato.setTextFill(Color.WHITE);
        return dato;
    }
}
