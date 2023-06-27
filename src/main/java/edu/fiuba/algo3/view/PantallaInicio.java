package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.CargadorDeEscena;
import edu.fiuba.algo3.controllers.ControladorDeSonido;
import javafx.stage.Stage;

public class PantallaInicio {
    public PantallaInicio(App app, Stage stage){
        app.setStage(stage);
        stage.setTitle("Algo Tower Defense");

        ControladorDeSonido controladorSonido = ControladorDeSonido.getInstance();
        controladorSonido.reproducirMusica("Age_of_Empires_1_Intro.mp3");

        CargadorDeEscena.cargarScene("/fxml/inicio.fxml",stage);
    }

}
