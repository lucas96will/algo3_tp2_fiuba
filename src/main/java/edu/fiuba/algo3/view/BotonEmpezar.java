package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeBoton;
import edu.fiuba.algo3.controllers.ControladorDeIngresarNombre;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BotonEmpezar extends Button {
    public static Button fijarBotonEmpezar(ControladorDeIngresarNombre controlador){
        ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
        controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")),null);
        Button boton = controladorDeBoton.obtenerBoton("", controlador.empezar());
        boton.setAlignment(Pos.CENTER);
        boton.setContentDisplay(ContentDisplay.CENTER);
        boton.setMnemonicParsing(false);
        boton.setStyle("-fx-background-radius: 10; -fx-background-color: rgb(255, 206, 91); -fx-padding: 20px 100px 18px 100px;");
        boton.setText("Empezar");
        boton.setTextAlignment(TextAlignment.CENTER);
        boton.setTextFill(Color.WHITE);
        boton.setTranslateX(420);
        boton.setTranslateY(550);
        boton.setFont(new Font(36));
        boton.setId("empezar");
        return boton;
    }
}
