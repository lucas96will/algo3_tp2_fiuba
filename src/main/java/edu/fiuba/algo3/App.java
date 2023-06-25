package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.CargadorDeEscena;
import edu.fiuba.algo3.controllers.ControladorDeSonido;
import edu.fiuba.algo3.view.PantallaInicio;
import javafx.application.Application;
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
        PantallaInicio pantallaInicio = new PantallaInicio(this, stage); //Hay que enchufarle nuestro modelo, no usar clases de otros 25/6
    }

    public static Stage obtenerStage () {return App.stage;}

    public void setStage(Stage unStage){ stage = unStage;}
}
