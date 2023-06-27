package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CargadorDeEscena {

    public static void cargarScene (String otraScene, Stage stage) {
        Scene scene = new Scene(CargadorDeEscena.cargarArchivo(otraScene));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setFullScreen(true);
        stage.show();
    }

    private static Parent cargarArchivo (String nombreDeScene) {
        Parent root;
        try {
            root = FXMLLoader.load((App.class.getResource(nombreDeScene)));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }

}
