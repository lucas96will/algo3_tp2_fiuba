package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.CargadorDeEscena;
import javafx.stage.Stage;

public class IngresarNombre {
    public IngresarNombre(App app, Stage stage){
        app.setStage(stage);
        CargadorDeEscena.cargarScene("/fxml/ingresarNombre.fxml",stage);
    }
}
