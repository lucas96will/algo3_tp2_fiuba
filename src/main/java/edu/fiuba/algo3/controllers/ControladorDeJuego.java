package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class ControladorDeJuego implements Initializable {

    @FXML private GridPane mapaGrid;
    @FXML private GridPane opcionesGrid;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapaGrid.setStyle("-fx-background-color: #28752c;");
        List<Parcela> terreno = Juego.getInstance().obtenerParcelas();
        terreno.forEach(parcela -> {
            URL url = getClass().getResource("/images/"+ parcela.getClass().getSimpleName() + ".png");
            Button btnTerreno = new Button();
            ImageView parcelaBackground = new ImageView();
            Image image = new Image(url.toString());
            parcelaBackground.setImage(image);
            parcelaBackground.setFitHeight(50);
            parcelaBackground.setFitWidth(50);
            btnTerreno.setPadding(new Insets(-1));
            btnTerreno.setGraphic(parcelaBackground);
            btnTerreno.setOnAction(this::construir);
            btnTerreno.setAlignment(Pos.CENTER);
            btnTerreno.setId(Integer.toString(parcela.obtenerPosicion().obtenerFila()).concat(Integer.toString(parcela.obtenerPosicion().obtenerColumna())));
            mapaGrid.add(btnTerreno,parcela.obtenerPosicion().obtenerColumna(),parcela.obtenerPosicion().obtenerFila());


            Button btnOpciones = new Button();
            btnOpciones.setPrefHeight(48);
            btnOpciones.setPrefWidth(48);
            btnOpciones.setPadding(new Insets(-1));
            btnOpciones.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
            btnOpciones.setVisible(false);
            btnOpciones.setOnAction(this::construirDefensa);
            opcionesGrid.add(btnOpciones,parcela.obtenerPosicion().obtenerColumna(),parcela.obtenerPosicion().obtenerFila());
        });

        opcionesGrid.setMouseTransparent(true);
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
    }

    public void construir(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int columna = GridPane.getColumnIndex(clickedButton);
        int fila = GridPane.getRowIndex(clickedButton);
        URL url = getClass().getResource("/images/TorrePlateada.png");
        Button defensa = (Button) getNodeFromGridPane(opcionesGrid, ++columna, fila);
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(url.toString());

        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(30);
        parcelaBackground.setFitWidth(30);
        defensa.setGraphic(parcelaBackground);
        defensa.setAlignment(Pos.CENTER);
        defensa.setStyle("-fx-background-color: rgba(0,0,0,0.6);");

        defensa.setVisible(true);
        opcionesGrid.setVisible(true);
        opcionesGrid.setMouseTransparent(false);
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void construirDefensa(ActionEvent event) {
        System.out.printf("click");
        Button clickedButton = (Button) event.getSource();
        clickedButton.setGraphic(null);
        int columna = GridPane.getColumnIndex(clickedButton);
        int fila = GridPane.getRowIndex(clickedButton);
        URL url = getClass().getResource("/images/TorrePlateada.png");
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(url.toString());

        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(33);
        parcelaBackground.setFitWidth(33);
        GridPane.setValignment(parcelaBackground, VPos.CENTER);
        GridPane.setHalignment(parcelaBackground, HPos.CENTER);
        mapaGrid.add(parcelaBackground,--columna,fila);

        clickedButton.setVisible(false);
        opcionesGrid.setVisible(false);
        opcionesGrid.setMouseTransparent(true);
    }
}
