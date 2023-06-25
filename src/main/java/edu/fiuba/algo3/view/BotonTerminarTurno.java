package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeBoton;
import edu.fiuba.algo3.controllers.ControladorDeIngresarNombre;
import edu.fiuba.algo3.controllers.ControladorDeJuego;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BotonTerminarTurno extends Button {
    public static Button fijarBotonTerminarTurno(ControladorDeJuego controlador){
        ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
        controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")),null);
        Button boton = controladorDeBoton.obtenerBoton("", controlador.terminarTurno());
        boton.setAlignment(Pos.CENTER);
        boton.setContentDisplay(ContentDisplay.CENTER);
        boton.setMnemonicParsing(false);
        boton.setStyle("-fx-background-radius: 10; -fx-background-color: rgb(255, 206, 91); -fx-padding: 20px 100px 18px 100px;");
        boton.setText("Terminar turno");
        boton.setTextAlignment(TextAlignment.CENTER);
        boton.setTextFill(Color.WHITE);
        boton.setTranslateX(18);
        boton.setTranslateY(90);
        boton.setFont(new Font(25));
        boton.setId("terminarTurno");
        return boton;
    }
}
