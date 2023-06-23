package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class ControladorDeJuego implements Initializable {

    private GridPane mapaGrid;
    private GridPane opcionesGrid;
    @FXML private Button btnTerminarTurno;
    @FXML private VBox vBoxDatos;
    @FXML private AnchorPane datosJugador;
    private GridPane enemigosGrid;
    @FXML private StackPane stackPane;

    private int colGrid;
    private int filGrid;
    private List<Button> btnDefensas = new ArrayList<>();
    private Posicion lugarDeConstruccion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Juego.getInstance().cargarJugador(Jugador.getInstance());
        Juego.getInstance().iniciar();

        Juego.getInstance().terminarTurno();

        Jugador jugador = Jugador.getInstance();
        String nombreJugador = jugador.obtenerNombreJugador();
        Label nombre = new Label(nombreJugador);
        nombre.setTextFill(Color.WHITE);
        vBoxDatos.getChildren().add(nombre);
        int vidaJugador = jugador.obtenerVidaJugador();
        Label vida = new Label();
        vida.setTextFill(Color.WHITE);
        vida.setText(String.valueOf(vidaJugador));
        vBoxDatos.getChildren().add(vida);
        Label creditos = new Label();
        creditos.setText(String.valueOf(jugador.valorCreditos()));
        creditos.setTextFill(Color.WHITE);
        vBoxDatos.getChildren().add(creditos);
        Label turnos = new Label();
        turnos.setText(String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        turnos.setTextFill(Color.WHITE);
        vBoxDatos.getChildren().add(turnos);
        ImageView terminarTurnoBackground = new ImageView();
        URL urlTerminarTurno = getClass().getResource("/images/TerminarTurno.png");
        terminarTurnoBackground.setImage(new Image(urlTerminarTurno.toString()));
        terminarTurnoBackground.setFitHeight(107);
        terminarTurnoBackground.setFitWidth(350);
        btnTerminarTurno.setGraphic(terminarTurnoBackground);
        btnTerminarTurno.setAlignment(Pos.CENTER);
        URL urlimagenDatos = getClass().getResource("/images/Lateral.png");
        Image imagenDatos = new Image(urlimagenDatos.toString());
        BackgroundImage fondoDatos = new BackgroundImage(imagenDatos, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        datosJugador.setBackground(new Background(fondoDatos));

        ControladorDeGrilla controladorDeGrillaParcelas = new ControladorDeGrilla();
        controladorDeGrillaParcelas.initialize((App.class.getResource("/fxml/grilla.fxml")),null);
        EventHandler construir = event -> {
                Button clickedButton = (Button) event.getSource();
                lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton),GridPane.getColumnIndex(clickedButton));
                clickedButton.setStyle("-fx-background-color: rgba(0,0,0,8);");
                List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
                Button defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(0).obtenerColumna(), posiciones.get(0).obtenerFila());
                btnDefensas.add(defensa);
                configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorrePlateada.png"));
                defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(1).obtenerColumna(), posiciones.get(1).obtenerFila());
                btnDefensas.add(defensa);
                configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorreBlanca.png"));
                defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(2).obtenerColumna(), posiciones.get(2).obtenerFila());
                btnDefensas.add(defensa);
                configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TrampaDeArena.png"));
                opcionesGrid.setVisible(true);
                opcionesGrid.setMouseTransparent(false);
        };
        List<Posicionable> parcelas = (List<Posicionable>) (List<?>) Juego.getInstance().obtenerParcelas();
        mapaGrid = controladorDeGrillaParcelas.obtenerGrillaDelTerreno(parcelas,construir);
        stackPane.getChildren().add(mapaGrid);
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

        ControladorDeGrilla controladorDeGrillaDefensas = new ControladorDeGrilla();
        controladorDeGrillaDefensas.initialize((App.class.getResource("/fxml/grilla.fxml")),null);
        EventHandler construirDefensas = event -> {
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
            ControladorDeSonido.getInstance().modificarVolumenEfecto(1);
            ControladorDeSonido.getInstance().reproducirEfecto("sonido_torre.mp3");
            opcionesGrid.setVisible(false);
            opcionesGrid.setMouseTransparent(true);

        };
        opcionesGrid = controladorDeGrillaDefensas.obtenerGrillaSuperpuestas(filGrid,colGrid,construirDefensas);
        opcionesGrid.setMouseTransparent(true);
        opcionesGrid.setMouseTransparent(true);
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().add(opcionesGrid);


        ControladorDeGrilla controladorDeGrillaEnemigos = new ControladorDeGrilla();
        controladorDeGrillaEnemigos.initialize((App.class.getResource("/fxml/grilla.fxml")),null);
        enemigosGrid = controladorDeGrillaEnemigos.obtenerGrillaSuperpuestas(filGrid,colGrid,null);
        enemigosGrid.setMouseTransparent(true);
        stackPane.getChildren().add(enemigosGrid);
    }


    private void configurarBotonDeConstruccion(Button defensa, URL urlImagen) {
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(urlImagen.toString());
        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(33);
        parcelaBackground.setFitWidth(33);
        defensa.setPrefWidth(45);
        defensa.setPrefHeight(45);
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

}
