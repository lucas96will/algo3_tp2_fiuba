package edu.fiuba.algo3.controllers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorDeDato implements Initializable {

    @FXML
    private HBox dato;
    @FXML private ImageView icono;
    @FXML private Label valor;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(location);
        try {
            dato = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icono = (ImageView) dato.getChildren().get(0);
        valor = (Label) dato.getChildren().get(1);
        loader.setController(this);
    }

    public HBox obtenerDato(URL path, String informacion) {
        icono.setImage(new Image(path.toString()));
        icono.setFitWidth(30);
        icono.setFitHeight(30);
        valor.setText(informacion);
        valor.setTextFill(Color.WHITE);
        valor.setFont(new Font("System Bold", 25));
        dato.setTranslateX(10);
        return dato;
    }

    public void unirPropiedad(StringProperty property){
        property.bindBidirectional(valor.textProperty());
    }

}
