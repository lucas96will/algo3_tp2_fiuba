package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeConfiguracion;
import edu.fiuba.algo3.controllers.ControladorDeDato;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;

public class Configuracion extends VBox {
    public static VBox fijarConfiguracion(){
        ControladorDeConfiguracion controladorDeConfiguracion = new ControladorDeConfiguracion();
        controladorDeConfiguracion.initialize((App.class.getResource("/fxml/configuracion.fxml")),null);
        return controladorDeConfiguracion.obtenerConfiguracion();
    }
}
