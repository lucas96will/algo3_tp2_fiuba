package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Cobrable.Cobrable;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Factory.ParcelaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaPerdida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaSigueJugando;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import edu.fiuba.algo3.view.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ControladorDeJuego implements Initializable {

    private GridPane mapaGrid;
    private GridPane opcionesGrid;
    private GridPane enemigosGrid;
    private int colGrid;
    private int filGrid;
    private List<Button> btnDefensas = new ArrayList<>();
    private Posicion lugarDeConstruccion;
    private VBox opcionesConfiguracion;
    @FXML private Button btnTerminarTurno;
    @FXML private VBox vBoxDatos;
    @FXML private AnchorPane datosJugador;
    @FXML private StackPane stackPane;
    @FXML private AnchorPane botonera;
    @FXML private ImageView configuracion;

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
        configurarConfiguracion();
        configuracion.setOnMouseClicked(configuracion());
    }

    private void configurarBotonDeConstruccion(Button defensa, URL urlImagen, EventHandler<ActionEvent> evento) {
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(urlImagen.toString());
        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(47.5);
        parcelaBackground.setFitWidth(47.5);
        defensa.setPrefWidth(64.8);
        defensa.setPrefHeight(64.8);
        defensa.setGraphic(parcelaBackground);
        defensa.setAlignment(Pos.CENTER);
        defensa.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        defensa.setOnAction(evento);
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
            posiciones.add(new Posicion((row), col));
            posiciones.add(new Posicion((row + 1), col));
            posiciones.add(new Posicion((row + 2), col));
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
            parcelaBackground.setFitHeight(47.5);
            parcelaBackground.setFitWidth(47.5);

            Posicion pos = new Posicion(lugarDeConstruccion.obtenerFila(), lugarDeConstruccion.obtenerColumna());
            String construible = parcelaBackground.getImage().getUrl();
            int primerIndice = Math.max(construible.lastIndexOf('/'), construible.lastIndexOf('\\')) + 1;
            int ultimoIndice = construible.lastIndexOf('.');
            construible = construible.substring(primerIndice, ultimoIndice);

            try{
                if (construible.equals("TrampaDeArena")) {
                    TrampaDeArena trampa = new TrampaDeArena();
                    Juego.getInstance().construir(trampa, pos);
                    ControladorDeSonido.getInstance().reproducirEfecto("sonido_torre_construida.mp3");
                } else {
                    DefensaFactory factoryDefensa = new DefensaFactory();
                    Defensa defensa = factoryDefensa.obtenerDefensa(construible, pos);
                    Jugador.getInstance().comprar(defensa);
                    Juego.getInstance().construir(defensa);
                    ControladorDeSonido.getInstance().reproducirEfecto("sonido_torre_construida.mp3");

                }
            } catch (RuntimeException e){
                ocultarOpcionesConstruir(btnDefensas, opcionesGrid);
                ControladorDeSonido.getInstance().reproducirEfecto("sonido_jugador_al_no_poder_comprar.mp3");
                System.out.println(e.getMessage());
                return;
            }


            GridPane.setValignment(parcelaBackground, VPos.CENTER);
            GridPane.setHalignment(parcelaBackground, HPos.CENTER);
            ((Button) getNodeFromGridPane(mapaGrid, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila())).setMouseTransparent(true);
            mapaGrid.add(parcelaBackground, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            actualizarRecursos();
            ocultarOpcionesConstruir(btnDefensas, opcionesGrid);

        };
    }

    private void ocultarOpcionesConstruir(List<Button> opciones, GridPane grid){
        opciones.forEach(btn -> {
            btn.setGraphic(null);
            btn.setVisible(false);
        });
        grid.setVisible(false);
        grid.setMouseTransparent(true);
    }

    private EventHandler<ActionEvent> cancelarConstruccion() {
        return event -> {
            btnDefensas.forEach(btn -> {
                btn.setGraphic(null);
                btn.setVisible(false);
            });
            ControladorDeSonido.getInstance().reproducirEfecto("Cancelar.mp3");
            opcionesGrid.setVisible(false);
            opcionesGrid.setMouseTransparent(true);
        };
    }

    private EventHandler<ActionEvent> construirOpcionesRocoso() {
        return event -> {
            ControladorDeSonido.getInstance().reproducirEfecto("Cancelar.mp3");
        };
    }

    private EventHandler<ActionEvent> construirOpcionesPasarela() {
        return event -> {
            Button clickedButton = (Button) event.getSource();
            String url = ((ImageView)(clickedButton.getGraphic())).getImage().getUrl();
            String tipoParcela = url.substring(url.lastIndexOf("/") + 1, url.indexOf("H"));
            lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton),GridPane.getColumnIndex(clickedButton));
            clickedButton.setStyle("-fx-background-color: rgba(0,0,0,8);");
            List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            int i = 0;
            Button defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TrampaDeArena.png"), construirDefensas());
            defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/CancelarConstruccion.png"), cancelarConstruccion());
            opcionesGrid.setVisible(true);
            opcionesGrid.setMouseTransparent(false);
        };
    }
    private EventHandler<ActionEvent> construirOpcionesTierra () {
        return event -> {
            Button clickedButton = (Button) event.getSource();
            lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton),GridPane.getColumnIndex(clickedButton));
            clickedButton.setStyle("-fx-background-color: rgba(0,0,0,8);");
            List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            int i = 0;
            Button defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorrePlateada.png"), construirDefensas());
            defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorreBlanca.png"), construirDefensas());
            defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/CancelarConstruccion.png"), cancelarConstruccion());
            opcionesGrid.setVisible(true);
            opcionesGrid.setMouseTransparent(false);
        };
    }

    public EventHandler<ActionEvent> terminarTurno(){
        return event -> {
            try {
                Juego.getInstance().terminarTurno();
                return;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }


            ControladorPantallaFinal controladorPantallaFinal = new ControladorPantallaFinal();
            if (Juego.getInstance().estado().equals(new EstadoPartidaGanada())){
                controladorPantallaFinal.configurarMensajeFinal("¡Ganaste!");
            } else if (Juego.getInstance().estado().equals(new EstadoPartidaPerdida())) {
                controladorPantallaFinal.configurarMensajeFinal("¡Perdiste!");
            }
            new PantallaFinal(App.getInstance(), App.obtenerStage());
        };
    }

    public EventHandler<MouseEvent> configuracion(){
        return event -> {
            opcionesConfiguracion.setVisible(!opcionesConfiguracion.isVisible());
            ControladorDeSonido.getInstance().reproducirEfecto(Constantes.SONIDO_EFECTO_CLICK_GENERICO);
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
        mapaGrid = Grilla.fijarGrilla(parcelas, construirOpcionesTierra(), construirOpcionesPasarela(), construirOpcionesRocoso());
        stackPane.getChildren().add(mapaGrid);
        setearDimensionesDeGrilla();
    }

    private void configurarGrillaDefensa(){
        opcionesGrid = Grilla.fijarGrillaSuperpuestas(filGrid,colGrid);
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().add(opcionesGrid);
    }

    private void configurarGrillaEnemigos() {
        enemigosGrid = Grilla.fijarGrillaSuperpuestas(filGrid,colGrid);
        stackPane.getChildren().add(enemigosGrid);
    }

    private void configurarConfiguracion() {
        opcionesConfiguracion = Configuracion.fijarConfiguracion();
        opcionesConfiguracion.setVisible(false);
        opcionesConfiguracion.setTranslateY(-340);
        opcionesConfiguracion.setTranslateX(160);
        vBoxDatos.getChildren().add(opcionesConfiguracion);
    }


    private void actualizarRecursos() {
       List<Node> children = vBoxDatos.getChildren();
       Label valor = (Label) ((HBox)children.get(2)).getChildren().get(1);
       valor.setText(String.valueOf(Jugador.getInstance().valorCreditos()));
    }
}
