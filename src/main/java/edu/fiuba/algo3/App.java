package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.CargadorDeEscena;
import edu.fiuba.algo3.controllers.ControladorDeSonido;
import edu.fiuba.algo3.view.PantallaInicio;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
        new PantallaInicio(this, stage);
    }

    public static Stage obtenerStage () {return App.stage;}

    public void setStage(Stage unStage){ stage = unStage;}
}
