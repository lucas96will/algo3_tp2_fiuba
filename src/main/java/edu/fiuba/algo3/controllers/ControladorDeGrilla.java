package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorDeGrilla implements Initializable {

    @FXML private GridPane gridpane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(location);
        try {
            gridpane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loader.setController(this);
    }

    public GridPane obtenerGrillaDelTerreno(List<Posicionable> posicionables, EventHandler eventTierra, EventHandler eventPasarela, EventHandler eventRocoso) {
        posicionables.forEach(posicionable -> {
            ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
            controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")), null);
            Button boton;
            String parcela = posicionable.getClass().getSimpleName();
            EventHandler event;
            if(parcela.equals("Tierra")){
                event = eventTierra;
            } else if(parcela.equals("Pasarela")){
                event = eventPasarela;
            } else{
                event = eventRocoso;
            }
            boton = controladorDeBoton.obtenerBoton(
                    "/images/" + parcela + ".png", event);
            configurarBotonGrilla(boton);
            ImageView backgroundDefault = (ImageView) boton.getGraphic();

            boton.setOnMouseEntered(eventMouse ->{
                ImageView backgroundHover = new ImageView(new Image(getClass().getResource("/images/"+ parcela +"Hover.png").toString()));
                backgroundHover.setFitWidth(boton.getPrefHeight());
                backgroundHover.setFitHeight(boton.getPrefHeight());
                boton.setGraphic(backgroundHover);
                ((ImageView)(boton.getGraphic())).setFitHeight(boton.getPrefHeight());
                ((ImageView)(boton.getGraphic())).setFitWidth(boton.getPrefWidth());
            });
            boton.setOnMouseExited(eventMouse ->{
                boton.setGraphic(backgroundDefault);
                ((ImageView)(boton.getGraphic())).setFitHeight(boton.getPrefHeight());
                ((ImageView)(boton.getGraphic())).setFitWidth(boton.getPrefWidth());
            });
            
            gridpane.add(boton, posicionable.obtenerPosicion().obtenerColumna(), posicionable.obtenerPosicion().obtenerFila());
        });
        return this.gridpane;
    }

    public GridPane obtenerGrillaSuperpuestas(int fila, int columna) {
        for (int i = 1; i <= fila; i++){
            for (int j = 1; j <= columna; j++){
                ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
                controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")), null);
                Button boton = controladorDeBoton.obtenerBoton("", null);
                configurarBotonGrilla(boton);
                boton.setVisible(false);
                gridpane.add(boton, j, i);
            }
        }
        gridpane.setMouseTransparent(true);
        return this.gridpane;
    }

    private void configurarBotonGrilla(Button boton) {
        boton.setPrefWidth(72);
        boton.setPrefHeight(72);
        boton.setFocusTraversable(false);
        ((ImageView)(boton.getGraphic())).setFitHeight(boton.getPrefHeight());
        ((ImageView)(boton.getGraphic())).setFitWidth(boton.getPrefWidth());
        boton.setStyle("-fx-background-color: transparent;");
        boton.setPadding(new Insets(0));
        boton.setAlignment(Pos.CENTER);
    }
}
