package edu.fiuba.algo3.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeBoton implements Initializable {
    @FXML private Button boton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(location);
        try {
            boton = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader.setController(this);
    }

    public Button obtenerBoton(String path, EventHandler event) {
        ImageView background = new ImageView();
        Image image = new Image(String.valueOf(getClass().getResource(path)));
        background.setImage(image);
        boton.setGraphic(background);
        boton.setOnAction(event);
        return this.boton;
    }
}
