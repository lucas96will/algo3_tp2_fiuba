package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.CargadorDeEscena;
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
        App.stage = stage;
        stage.setTitle("Algo Tower Defense");
        CargadorDeEscena.cargarScene("/fxml/inicio.fxml",stage);
    }

    public static Stage obtenerStage () {return App.stage;}
}
