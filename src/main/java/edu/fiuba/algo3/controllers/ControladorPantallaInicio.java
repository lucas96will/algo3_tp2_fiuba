package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPantallaInicio implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void jugar(){
        try {
            String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
            String rutaJsonMapa = "data/jsonTests/mapa.json";
            Juego.getInstance().cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
            CargadorDeEscena.cargarScene("/fxml/juego.fxml", App.obtenerStage());
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
