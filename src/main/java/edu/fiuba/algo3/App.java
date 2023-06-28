package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.CargadorDeEscena;
import edu.fiuba.algo3.controllers.ControladorDeSonido;
import edu.fiuba.algo3.view.Constantes;
import edu.fiuba.algo3.view.PantallaInicio;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class App extends Application {

    private static Stage stage;
    private static App app;

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage){
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.centerOnScreen();
        new PantallaInicio(this, stage);
    }

    public static Stage obtenerStage () {return App.stage;}

    public void setStage(Stage unStage){ stage = unStage;}

    public void abrirInformacionDelJuego() throws URISyntaxException {
        URL url = getClass().getResource(Constantes.RUTA_INFORMACION_JUEGO);
        File file = new File(url.toURI());
        HostServices host = getHostServices();
        host.showDocument(file.getAbsolutePath());
    }
}
