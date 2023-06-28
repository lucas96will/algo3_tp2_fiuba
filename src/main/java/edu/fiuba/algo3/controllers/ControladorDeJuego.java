package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Posicionable.Posicionable;
import edu.fiuba.algo3.view.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class ControladorDeJuego implements Initializable {

    private GridPane mapaGrid;
    private GridPane opcionesGrid;
    private GridPane enemigosGrid;
    private int colGrid;
    private int filGrid;
    private List<Button> btnDefensas = new ArrayList<>();
    private Posicion lugarDeConstruccion;
    private VBox opcionesConfiguracion;
    DatosJugadorObservable datosJugadorObservable;
    @FXML
    private StackPane ventana;
    @FXML
    private Button btnTerminarTurno;
    @FXML
    private VBox vBoxDatos;
    @FXML
    private AnchorPane datosJugador;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane botonera;
    @FXML
    private ImageView configuracion;
    @FXML
    private StackPane display;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datosJugadorObservable = new DatosJugadorObservable();
        Jugador jugador = Jugador.getInstance();
        configurarDatosJugador((App.class.getResource("/images/Nombre.png")), jugador.obtenerNombreJugador(), datosJugadorObservable.nombreProperty());
        configurarDatosJugador((App.class.getResource("/images/Vida.png")), String.valueOf(jugador.obtenerVidaJugador()), datosJugadorObservable.vidaJugadorProperty());
        configurarDatosJugador((App.class.getResource("/images/Credito.png")), String.valueOf(jugador.valorCreditos()), datosJugadorObservable.creditoProperty());
        configurarDatosJugador((App.class.getResource("/images/Turno.png")), String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()), datosJugadorObservable.turnoProperty());
        configurarDatosJugador((App.class.getResource("/images/Defensa.png")), String.valueOf(jugador.obtenerDefensas().size()), datosJugadorObservable.cantDefensasProperty());
        //configurarDatosJugador((App.class.getResource("/images/Enemigo.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().size()), datosJugadorObservable.cantEnemigosProperty());
        configurarDatosJugador((App.class.getResource("/images/Hormiga.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Hormiga")).collect(Collectors.toList()).size()), datosJugadorObservable.cantHormigaProperty());
        configurarDatosJugador((App.class.getResource("/images/Arania.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Arania")).collect(Collectors.toList()).size()), datosJugadorObservable.cantAraniaProperty());
        configurarDatosJugador((App.class.getResource("/images/Topo.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Topo")).collect(Collectors.toList()).size()), datosJugadorObservable.cantTopoProperty());
        configurarDatosJugador((App.class.getResource("/images/Buho.png")), String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Lechuza")).collect(Collectors.toList()).size()), datosJugadorObservable.cantLechuzaProperty());
        configurarMensaje();
        configurarBotonTerminarTurno();
        configurarPanelDatosJugador();
        configurarGrillaTerreno();
        configurarGrillaDefensa();
        configurarGrillaEnemigos();
        configurarConfiguracion();
        configurarDisplay();
        configuracion.setOnMouseClicked(configuracion());
    }

    private void configurarBotonDeConstruccion(Button boton, URL urlImagen, EventHandler<ActionEvent> evento) {
        ImageView parcelaBackground = new ImageView();
        Image image = new Image(urlImagen.toString());
        parcelaBackground.setImage(image);
        parcelaBackground.setFitHeight(47.5);
        parcelaBackground.setFitWidth(47.5);
        boton.setPrefWidth(64.8);
        boton.setPrefHeight(64.8);
        boton.setGraphic(parcelaBackground);
        boton.setAlignment(Pos.CENTER);
        boton.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        boton.setOnAction(evento);
        boton.setVisible(true);
    }

    private List<Posicion> obtenerPosicionesValidas(int col, int row) {
        List<Posicion> posiciones = new ArrayList<>();
        if ((this.colGrid == col)) {
            col--;
        } else {
            col++;
        }
        if (row == this.filGrid) {
            posiciones.add(new Posicion((row - 2), col));
            posiciones.add(new Posicion((row - 1), col));
            posiciones.add(new Posicion((row), col));
        } else if ((row + 1) == this.filGrid) {
            posiciones.add(new Posicion((row - 1), col));
            posiciones.add(new Posicion((row), col));
            posiciones.add(new Posicion((row + 1), col));

        } else {
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

            Posicion pos = new Posicion(lugarDeConstruccion.obtenerFila(), lugarDeConstruccion.obtenerColumna());
            String construible = parcelaBackground.getImage().getUrl();
            int primerIndice = Math.max(construible.lastIndexOf('/'), construible.lastIndexOf('\\')) + 7;
            int ultimoIndice = construible.lastIndexOf('.');
            construible = construible.substring(primerIndice, ultimoIndice);
            parcelaBackground.setImage(new Image(getClass().getResource("/images/" + construible + ".png").toString()));
            parcelaBackground.setFitHeight(47.5);
            parcelaBackground.setFitWidth(47.5);

            try {
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
            } catch (RuntimeException e) {
                ocultarOpcionesConstruir(btnDefensas, opcionesGrid);
                ControladorDeSonido.getInstance().reproducirEfecto("sonido_jugador_al_no_poder_comprar.mp3");
                datosJugadorObservable.mensajeProperty().set("RECURSOS INSUFICIENTES");
                PanelDatos.obtenerControladorMensaje().animar();
                System.out.println(e.getMessage());
                return;
            }


            GridPane.setValignment(parcelaBackground, VPos.CENTER);
            GridPane.setHalignment(parcelaBackground, HPos.CENTER);
            ((Button) getNodeFromGridPane(mapaGrid, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila())).setMouseTransparent(true);
            mapaGrid.add(parcelaBackground, lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            actualizarCreditosObservables();
            actualizarDefensasObservables();
            ocultarOpcionesConstruir(btnDefensas, opcionesGrid);

        };
    }

    private void configurarMensaje() {
        Pane pane = PanelDatos.fijarMensaje("RECURSOS INSUFICIENTES", datosJugadorObservable.mensajeProperty());
        vBoxDatos.getChildren().add(pane);
    }

    private void ocultarOpcionesConstruir(List<Button> opciones, GridPane grid) {
        opciones.forEach(btn -> {
            btn.setOnMouseExited(null);
            btn.setOnMouseEntered(null);
            btn.setGraphic(null);
            btn.setVisible(false);
        });
        grid.setVisible(false);
        grid.setMouseTransparent(true);
    }

    private EventHandler<ActionEvent> cancelarConstruccion() {
        return event -> {
            btnDefensas.forEach(btn -> {
                btn.setOnMouseEntered(null);
                btn.setOnMouseExited(null);
                btn.setGraphic(null);
                btn.setVisible(false);
            });
            ControladorDeSonido.getInstance().reproducirEfecto("Cancelar.mp3");
            opcionesGrid.setVisible(false);
            opcionesGrid.setMouseTransparent(true);
        };
    }

    private void configurarBotonConstruccionHover(Button boton, ImageView backgroundDefault){
        boton.setOnMouseEntered(eventMouse ->{
            String path = backgroundDefault.getImage().getUrl();
            ImageView backgroundHover = new ImageView(new Image(getClass().getResource("/images/Precio"+ path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")) +".png").toString()));
            backgroundHover.setFitWidth(47.5);
            backgroundHover.setFitHeight(47.5);
            boton.setGraphic(backgroundHover);
        });
        boton.setOnMouseExited(eventMouse ->{
            backgroundDefault.setFitHeight(47.5);
            backgroundDefault.setFitWidth(47.5);
            boton.setGraphic(backgroundDefault);
        });
    }

    private EventHandler<ActionEvent> construirOpcionesRocoso() {
        return event -> {
            ControladorDeSonido.getInstance().reproducirEfecto("Cancelar.mp3");
        };
    }

    private EventHandler<ActionEvent> construirOpcionesPasarela() {
        return event -> {
            ControladorDeSonido.getInstance().reproducirEfecto("building_house2.wav");
            Button clickedButton = (Button) event.getSource();
            String url = ((ImageView) (clickedButton.getGraphic())).getImage().getUrl();
            String tipoParcela = url.substring(url.lastIndexOf("/") + 1, url.indexOf("H"));
            lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton), GridPane.getColumnIndex(clickedButton));
            clickedButton.setStyle("-fx-background-color: rgba(0,0,0,8);");
            List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            int i = 0;
            Button boton = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(boton);
            configurarBotonDeConstruccion(boton, getClass().getResource("/images/TrampaDeArena.png"), construirDefensas());
            ImageView backgroundDefault = (ImageView) boton.getGraphic();
            configurarBotonConstruccionHover(boton, backgroundDefault );

            Button boton2 = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(boton2);
            configurarBotonDeConstruccion(boton2, getClass().getResource("/images/CancelarConstruccion.png"), cancelarConstruccion());
            opcionesGrid.setVisible(true);
            opcionesGrid.setMouseTransparent(false);
        };
    }

    private EventHandler<ActionEvent> construirOpcionesTierra() {
        return event -> {
            ControladorDeSonido.getInstance().reproducirEfecto("building_house2.wav");
            Button clickedButton = (Button) event.getSource();
            lugarDeConstruccion = new Posicion(GridPane.getRowIndex(clickedButton), GridPane.getColumnIndex(clickedButton));
            clickedButton.setStyle("-fx-background-color: rgba(0,0,0,8);");
            List<Posicion> posiciones = obtenerPosicionesValidas(lugarDeConstruccion.obtenerColumna(), lugarDeConstruccion.obtenerFila());
            int i = 0;
            Button defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorrePlateada.png"), construirDefensas());
            ImageView backgroundDefault1 = (ImageView) defensa.getGraphic();
            configurarBotonConstruccionHover(defensa, backgroundDefault1 );

            defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/TorreBlanca.png"), construirDefensas());
            ImageView backgroundDefault2 = (ImageView) defensa.getGraphic();
            configurarBotonConstruccionHover(defensa, backgroundDefault2 );

            defensa = (Button) getNodeFromGridPane(opcionesGrid, posiciones.get(i).obtenerColumna(), posiciones.get(i++).obtenerFila());
            btnDefensas.add(defensa);
            configurarBotonDeConstruccion(defensa, getClass().getResource("/images/CancelarConstruccion.png"), cancelarConstruccion());
            opcionesGrid.setVisible(true);
            opcionesGrid.setMouseTransparent(false);
        };
    }
    private int frameActual = 0;
    private long tiempoFinal = 0;

    public EventHandler<ActionEvent> terminarTurno() {
        return event -> {
            ImageView sprite = new ImageView();
            ventana.getChildren().add(sprite);
            ControladorDeAnimacion animador = new ControladorDeAnimacion(61, 20, 2, sprite, "HormigaEnMovimiento", display, "TerminarTurno.mp3");
            animador.start();
            try {
                eliminarImagenEnemigosAntesDeTerminarTurno();

                Juego.getInstance().terminarTurno();
                agregarImagenEnemigosLuegoDeTerminarTurno();
                eliminarImagenesDeTorresDestruidas();
                datosJugadorObservable.actualizar();
                return;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            new PantallaFinal(App.getInstance(), App.obtenerStage());
        };
    }

    private void eliminarImagenEnemigosAntesDeTerminarTurno() {
        List<Node> nodosABorrar = enemigosGrid.getChildren().stream().filter(n -> n instanceof ImageView).collect(Collectors.toList());
        enemigosGrid.getChildren().removeAll(nodosABorrar);
    }

    private void agregarImagenEnemigosLuegoDeTerminarTurno() {
        List<Enemigo> enemigos = Juego.getInstance().obtenerEnemigos();
        for (Enemigo unEnemigo : enemigos) {
            String urlenemy = Constantes.urlImagenesEnemigos.get(unEnemigo.nombre());
            ImageView enemigo = new ImageView(getClass().getResource(urlenemy).toString());
            enemigo.setFitHeight(47.5);
            enemigo.setFitWidth(47.5);
            enemigosGrid.add(enemigo, unEnemigo.obtenerPosicion().obtenerColumna(), unEnemigo.obtenerPosicion().obtenerFila());
        }
    }

    private void eliminarImagenesDeTorresDestruidas() {
        List<Defensa> defensasEliminadas = Jugador.getInstance().obtenerDefensasEliminadas();
        Posicion posicionALimpiar;
        while (!defensasEliminadas.isEmpty()) {
            datosJugadorObservable.mensajeProperty().set("TORRE DESTRUIDA");
            PanelDatos.obtenerControladorMensaje().animar();
            posicionALimpiar = defensasEliminadas.get(0).obtenerPosicion();
            Button botoncito = ((Button) getNodeFromGridPane(mapaGrid, posicionALimpiar.obtenerColumna(), posicionALimpiar.obtenerFila()));
            botoncito.setMouseTransparent(false);
            eliminarImagenEn(posicionALimpiar);
            defensasEliminadas.remove(0);
        }
    }

    private void eliminarImagenEn(Posicion pos) {
        List<Node> children = mapaGrid.getChildren();
        ImageView imagenAEliminar = null;

        for(Node nodo : children) {
            int columna = mapaGrid.getColumnIndex(nodo);
            int fila = mapaGrid.getRowIndex(nodo);
            if(columna == pos.obtenerColumna() && fila == pos.obtenerFila() && nodo instanceof ImageView) {
                imagenAEliminar = (ImageView) nodo;
                break;
            }
        }

        mapaGrid.getChildren().remove(imagenAEliminar);
    }

    public EventHandler<MouseEvent> configuracion() {
        return event -> {
            opcionesConfiguracion.setVisible(!opcionesConfiguracion.isVisible());
            ControladorDeSonido.getInstance().reproducirEfecto(Constantes.SONIDO_EFECTO_CLICK_GENERICO);
        };
    }

    private void configurarBotonTerminarTurno() {
        btnTerminarTurno = BotonTerminarTurno.fijarBotonTerminarTurno(this);
        botonera.getChildren().add(btnTerminarTurno);
    }


    private void configurarDatosJugador(URL path, String dato, StringProperty property) {
        Pane pane = PanelDatos.fijarDatoJugador(path, dato, property);
        vBoxDatos.getChildren().add(pane);
    }


    private void configurarPanelDatosJugador() {
        BackgroundImage fondoDatos = new BackgroundImage(new Image(getClass().getResource("/images/Lateral.png").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(402, 700, false, false, false, true));
        datosJugador.setBackground(new Background(fondoDatos));
    }

    private void configurarGrillaTerreno() {
        List<Posicionable> parcelas = (List<Posicionable>) (List<?>) Juego.getInstance().obtenerParcelas();
        mapaGrid = Grilla.fijarGrilla(parcelas, construirOpcionesTierra(), construirOpcionesPasarela(), construirOpcionesRocoso());
        stackPane.getChildren().add(mapaGrid);
        setearDimensionesDeGrilla();
    }

    private void configurarGrillaDefensa() {
        opcionesGrid = Grilla.fijarGrillaSuperpuestas(filGrid, colGrid);
        opcionesGrid.setVisible(false);
        opcionesGrid.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().add(opcionesGrid);
    }

    private void configurarGrillaEnemigos() {
        enemigosGrid = Grilla.fijarGrillaSuperpuestas(filGrid, colGrid);
        stackPane.getChildren().add(enemigosGrid);
    }

    private void configurarConfiguracion() {
        opcionesConfiguracion = Configuracion.fijarConfiguracion();
        opcionesConfiguracion.setVisible(false);
        opcionesConfiguracion.setTranslateY(configuracion.getTranslateY() + 50);
        opcionesConfiguracion.setTranslateX(configuracion.getTranslateX() - 73);
        datosJugador.getChildren().add(opcionesConfiguracion);
    }

    private void configurarDisplay() {
        display.setVisible(false);
    }



    private void actualizarCreditosObservables() {
        datosJugadorObservable.setCredito(String.valueOf(Jugador.getInstance().valorCreditos()));
    }

    private void actualizarDefensasObservables() {
        datosJugadorObservable.setCantDefensas(String.valueOf(Jugador.getInstance().obtenerDefensas().size()));
    }


}
