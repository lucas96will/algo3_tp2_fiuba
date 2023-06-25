package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.net.URL;
import java.util.ResourceBundle;


public class ControladorPantallaInicio implements Initializable {

    @FXML private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBotonJugar();
    }

    private EventHandler<ActionEvent> jugar(){
        return event -> {
            try {
                String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
                String rutaJsonMapa = "data/jsonTests/mapa.json";
                Juego.getInstance().cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
                CargadorDeEscena.cargarScene("/fxml/juego.fxml", App.obtenerStage());
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        };
    }

    private void configurarBotonJugar() {
        ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
        controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")),null);
        Button boton = controladorDeBoton.obtenerBoton("",jugar());
        boton.setAlignment(Pos.CENTER);
        boton.setContentDisplay(ContentDisplay.CENTER);
        boton.setMnemonicParsing(false);
        boton.setStyle("-fx-background-radius: 10; -fx-background-color: rgb(255, 206, 91); -fx-padding: 20px 100px 18px 100px;");
        boton.setText("Jugar");
        boton.setTextAlignment(TextAlignment.CENTER);
        boton.setTextFill(Color.WHITE);
        boton.setTranslateY(200);
        boton.setFont(new Font(36));
        boton.setId("botonJugar");
        stackPane.getChildren().add(boton);
    }
}
