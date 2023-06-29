package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.CargadorDeEscena;
import javafx.stage.Stage;

public class PantallaFinal {
    public PantallaFinal(App app, Stage stage){
        app.setStage(stage);
        CargadorDeEscena.cargarScene("/fxml/final.fxml",stage);
    }
}
