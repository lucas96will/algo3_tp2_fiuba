package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.CargadorDeEscena;
import javafx.stage.Stage;

public class PantallaJuego {
    public PantallaJuego(App app, Stage stage){
        app.setStage(stage);
        CargadorDeEscena.cargarScene("/fxml/juego.fxml",stage);
    }
}
