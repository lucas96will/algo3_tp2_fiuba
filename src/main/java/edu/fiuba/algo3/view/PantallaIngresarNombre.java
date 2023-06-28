package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.CargadorDeEscena;
import edu.fiuba.algo3.controllers.ControladorDeIngresarNombre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PantallaIngresarNombre {
    public PantallaIngresarNombre(App app, Stage stage){
        CargadorDeEscena.cargarScene("/fxml/ingresarNombre.fxml",stage);
    }
}
