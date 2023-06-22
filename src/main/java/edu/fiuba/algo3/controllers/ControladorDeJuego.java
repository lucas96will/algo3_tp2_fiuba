package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class ControladorDeJuego implements Initializable {

    @FXML private GridPane mapaGrid;
    @FXML private GridPane opcionesGrid;
    @FXML private SplitPane pnlDatosJugador;
    private int colGrid;
    private int filGrid;
    private List<Button> btnDefensas = new ArrayList<>();
    private Posicion lugarDeConstruccion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Jugador jugador = Jugador.getInstance();
        int vidaJugador = jugador.obtenerVidaJugador();
        Label vida = new Label();
        vida.setText(String.valueOf(vidaJugador));
        pnlDatosJugador.getItems().add(vida);
        Label creditos = new Label();
        creditos.setText(String.valueOf(jugador.valorCreditos()));
        pnlDatosJugador.getItems().add(creditos);
        Label turnos = new Label();
        turnos.setText(String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        pnlDatosJugador.getItems().add(turnos);


        mapaGrid.setStyle("-fx-background-color: #28752c;");
        List<Parcela> terreno = Juego.getInstance().obtenerParcelas();
        terreno.forEach(parcela -> {
            URL url = getClass().getResource("/images/" + parcela.getClass().getSimpleName() + ".png");
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
        filGrid = (int) mapaGrid.getChildren().stream()
                .mapToInt(GridPane::getRowIndex)
                .filter(Objects::nonNull)
                .distinct()
                .count();
        colGrid = (int) mapaGrid.getChildren().stream()
                .mapToInt(GridPane::getColumnIndex)
                .filter(Objects::nonNull)
                .distinct()
                .count();
        opcionesGrid.setMouseTransparent(true);
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
    }

    public void construir(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton),GridPane.getColumnIndex(clickedButton));
        clickedButton.setStyle("-fx-background-color: rgba(0,0,0,0.8);");
        List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
        Button defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(0).obtenerColumna(), posiciones.get(0).obtenerFila());
        btnDefensas.add(defensa);
        configurarBotomDeConstruccion(defensa, getClass().getResource("/images/TorrePlateada.png"));
        defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(1).obtenerColumna(), posiciones.get(1).obtenerFila());
        btnDefensas.add(defensa);
        configurarBotomDeConstruccion(defensa, getClass().getResource("/images/TorreBlanca.png"));
        defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(2).obtenerColumna(), posiciones.get(2).obtenerFila());
        btnDefensas.add(defensa);
        configurarBotomDeConstruccion(defensa, getClass().getResource("/images/TrampaDeArena.png"));
        opcionesGrid.setVisible(true);
        opcionesGrid.setMouseTransparent(false);
    }

    private void configurarBotomDeConstruccion(Button defensa, URL urlImagen) {
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(urlImagen.toString());
        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(30);
        parcelaBackground.setFitWidth(30);
        defensa.setGraphic(parcelaBackground);
        defensa.setAlignment(Pos.CENTER);
        defensa.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        defensa.setVisible(true);
    }

    private List<Posicion> obtenerPosicionesValidas (int col, int row) {
        List<Posicion> posiciones = new ArrayList<>();
        if ((this.colGrid == col)) {
            col--;
        } else {
            col++;
        }
        if (row == this.filGrid) {
            posiciones.add(new Posicion((row - 2),col));
            posiciones.add(new Posicion((row - 1),col));
            posiciones.add(new Posicion((row),col));
        } else if ((row + 1) == this.filGrid) {
            posiciones.add(new Posicion((row - 1),col));
            posiciones.add(new Posicion((row),col));
            posiciones.add(new Posicion((row + 1),col));
        } else{
            posiciones.add(new Posicion((row),col));
            posiciones.add(new Posicion((row + 1),col));
            posiciones.add(new Posicion((row + 2),col));
        }
        return posiciones;
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
        Button clickedButton = (Button) event.getSource();
        ImageView parcelaBackground = new ImageView();
        parcelaBackground.setImage(((ImageView)clickedButton.getGraphic()).getImage());
        parcelaBackground.setFitHeight(33);
        parcelaBackground.setFitWidth(33);
        GridPane.setValignment(parcelaBackground, VPos.CENTER);
        GridPane.setHalignment(parcelaBackground, HPos.CENTER);
        ((Button) getNodeFromGridPane(mapaGrid, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila())).setOnAction(null);
        mapaGrid.add(parcelaBackground,lugarDeConstruccion.obtenerColumna(),lugarDeConstruccion.obtenerFila());
        btnDefensas.forEach(btn -> {btn.setGraphic(null);btn.setVisible(false);});
        opcionesGrid.setVisible(false);
        opcionesGrid.setMouseTransparent(true);

    }
}
