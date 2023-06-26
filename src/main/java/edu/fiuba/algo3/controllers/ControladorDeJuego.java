package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaPerdida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaSigueJugando;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import edu.fiuba.algo3.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class ControladorDeJuego implements Initializable {

    private GridPane mapaGrid;
    private GridPane opcionesGrid;
    private GridPane enemigosGrid;
    private int colGrid;
    private int filGrid;
    private List<Button> btnDefensas = new ArrayList<>();
    private Posicion lugarDeConstruccion;
    @FXML private Button btnTerminarTurno;
    @FXML private VBox vBoxDatos;
    @FXML private AnchorPane datosJugador;
    @FXML private StackPane stackPane;
    @FXML private AnchorPane botonera;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Jugador jugador = Jugador.getInstance();

        configurarDatosJugador((App.class.getResource("/images/Nombre.png")),jugador.obtenerNombreJugador());
        configurarDatosJugador((App.class.getResource("/images/Vida.png")),String.valueOf(jugador.obtenerVidaJugador()));
        configurarDatosJugador((App.class.getResource("/images/Credito.png")), String.valueOf(jugador.valorCreditos()));
        configurarDatosJugador((App.class.getResource("/images/Turno.png")), String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        configurarDatosJugador((App.class.getResource("/images/Defensa.png")), String.valueOf(jugador.obtenerDefensas().size()));
        configurarDatosJugador((App.class.getResource("/images/Enemigo.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().size()));
        configurarBotonTerminarTurno();
        configurarPanelDatosJugador();
        configurarGrillaTerreno();
        configurarGrillaDefensa();
        configurarGrillaEnemigos();
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

    private void setearDimensionesDeGrilla() {
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
    }

    private EventHandler<ActionEvent> construirDefensas() {
        return event -> {
            Button clickedButton = (Button) event.getSource();
            ImageView parcelaBackground = new ImageView();
            parcelaBackground.setImage(((ImageView) clickedButton.getGraphic()).getImage());
            parcelaBackground.setFitHeight(33);
            parcelaBackground.setFitWidth(33);
            GridPane.setValignment(parcelaBackground, VPos.CENTER);
            GridPane.setHalignment(parcelaBackground, HPos.CENTER);
            ((Button) getNodeFromGridPane(mapaGrid, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila())).setMouseTransparent(true);
            mapaGrid.add(parcelaBackground, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            btnDefensas.forEach(btn -> {
                btn.setGraphic(null);
                btn.setVisible(false);
            });
            ControladorDeSonido controladorSonido = ControladorDeSonido.getInstance();
            controladorSonido.modificarVolumenEfecto(1);
            ControladorDeSonido.getInstance().reproducirEfecto("sonido_torre_construida.mp3");
            opcionesGrid.setVisible(false);
            opcionesGrid.setMouseTransparent(true);
        };
    }

    private EventHandler<ActionEvent> construirOpciones () {
        return event -> {
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
    }

    public EventHandler<ActionEvent> terminarTurno(){
        return event -> {
            Juego.getInstance().terminarTurno();
            if (!Juego.getInstance().estado().equals(new EstadoPartidaSigueJugando())) {
                ControladorPantallaFinal controladorPantallaFinal = new ControladorPantallaFinal();
                if (Juego.getInstance().estado().equals(new EstadoPartidaGanada())){
                    controladorPantallaFinal.configurarMensajeFinal("¡Ganaste!");
                } else if (Juego.getInstance().estado().equals(new EstadoPartidaPerdida())) {
                    controladorPantallaFinal.configurarMensajeFinal("¡Perdiste!");
                }
                new PantallaFinal(App.getInstance(), App.obtenerStage());
            }
        };
    }

    private void configurarBotonTerminarTurno() {
        btnTerminarTurno = BotonTerminarTurno.fijarBotonTerminarTurno(this);
        botonera.getChildren().add(btnTerminarTurno);
    }
    private void configurarDatosJugador(URL path, String dato) {
        Pane pane = PanelDatos.fijarDatoJugador(path, dato);
        vBoxDatos.getChildren().add(pane);
    }

    private void configurarPanelDatosJugador(){
        BackgroundImage fondoDatos = new BackgroundImage(new Image(getClass().getResource("/images/Lateral.png").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,new BackgroundSize(402, 700,false,false,false,true));
        datosJugador.setBackground(new Background(fondoDatos));
    }

    private void configurarGrillaTerreno(){
        List<Posicionable> parcelas = (List<Posicionable>) (List<?>) Juego.getInstance().obtenerParcelas();
        mapaGrid = Grilla.fijarGrilla(parcelas,construirOpciones());
        stackPane.getChildren().add(mapaGrid);
        setearDimensionesDeGrilla();
    }

    private void configurarGrillaDefensa(){
        opcionesGrid = Grilla.fijarGrillaSuperpuestas(filGrid,colGrid,construirDefensas());
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().add(opcionesGrid);
    }

    private void configurarGrillaEnemigos() {
        enemigosGrid = Grilla.fijarGrillaSuperpuestas(filGrid,colGrid,null);
        stackPane.getChildren().add(enemigosGrid);
    }

}
