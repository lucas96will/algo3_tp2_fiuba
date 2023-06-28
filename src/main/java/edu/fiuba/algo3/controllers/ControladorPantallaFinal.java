package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.view.BotonVolverAEmpezar;
import edu.fiuba.algo3.view.PantallaInicio;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPantallaFinal implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text resultado;
    private Button boton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarBotonEmpezar();
    }

    public EventHandler volverAEmpezar () {
        return event -> {
            ContadorTurnos.obtenerContador().resetear();
            new PantallaInicio(App.getInstance(), App.obtenerStage());
        };
    }


    private void configurarBotonEmpezar() {
        boton = BotonVolverAEmpezar.fijarBotonVolverAEmpezar(this, "Volver a empezar");
        if(Juego.getInstance().estado().equals(new EstadoPartidaGanada())){
            resultado.setText("GANASTE GUARDIAN DE LA BAHIA");
            resultado.translateXProperty().set(-300.0);
        } else {
            resultado.setText("PERDISTE MINNESOTA STICKER BALL");
            resultado.translateXProperty().set(-450.0);
        }
        anchorPane.getChildren().addAll(boton);
    }
}
