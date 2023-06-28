package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeDato;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PanelDatos extends Pane {

    public static Pane fijarDatoJugador(URL path, String dato, StringProperty propiedad){
        ControladorDeDato controladorDeDato = new ControladorDeDato();
        controladorDeDato.initialize((App.class.getResource("/fxml/dato.fxml")),null);
        controladorDeDato.unirPropiedad(propiedad);
        return controladorDeDato.obtenerDato(path,dato);
    }
}
