package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeDato;
import edu.fiuba.algo3.controllers.ControladorMensaje;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PanelDatos extends Pane {
    private static ControladorMensaje controladorMensaje = new ControladorMensaje();
    public static Pane fijarDatoJugador(URL path, String dato, StringProperty propiedad){
        ControladorDeDato controladorDeDato = new ControladorDeDato();
        controladorDeDato.initialize((App.class.getResource("/fxml/dato.fxml")),null);
        controladorDeDato.unirPropiedad(propiedad);
        return controladorDeDato.obtenerDato(path,dato);
    }

    public static Pane fijarMensaje(String mensaje, StringProperty propiedad) {
        controladorMensaje.initialize((App.class.getResource("/fxml/mensaje.fxml")), null);
        controladorMensaje.unirPropiedad(propiedad);
        return  controladorMensaje.obtenerMensaje(mensaje);
    }

    public static ControladorMensaje obtenerControladorMensaje() {
        return controladorMensaje;
    }
}
