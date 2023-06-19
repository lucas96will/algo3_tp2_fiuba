package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPantallaInicio implements Initializable {

    private final String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
    private final String rutaJsonMapa = "data/jsonTests/mapa.json";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void jugar(){
        try {
            Juego.getInstance().cargarConJson(rutaJsonEnemigos,rutaJsonMapa);

        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
