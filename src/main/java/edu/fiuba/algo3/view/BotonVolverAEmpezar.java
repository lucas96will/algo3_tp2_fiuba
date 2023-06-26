package edu.fiuba.algo3.view;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controllers.ControladorDeBoton;
import edu.fiuba.algo3.controllers.ControladorDeIngresarNombre;
import edu.fiuba.algo3.controllers.ControladorPantallaFinal;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class BotonVolverAEmpezar extends Button {
    public static Button fijarBotonVolverAEmpezar(ControladorPantallaFinal controlador, String mensaje){
        ControladorDeBoton controladorDeBoton = new ControladorDeBoton();
        controladorDeBoton.initialize((App.class.getResource("/fxml/boton.fxml")),null);
        Button boton = controladorDeBoton.obtenerBoton("", controlador.volverAEmpezar());
        boton.setAlignment(Pos.CENTER);
        boton.setContentDisplay(ContentDisplay.CENTER);
        boton.setMnemonicParsing(false);
        boton.setStyle("-fx-background-radius: 10; -fx-background-color: rgb(255, 206, 91); -fx-padding: 20px 100px 18px 100px;");
        boton.setText(mensaje);
        boton.setTextAlignment(TextAlignment.CENTER);
        boton.setTextFill(Color.WHITE);
        boton.setTranslateX(350);
        boton.setTranslateY(550);
        boton.setFont(new Font(36));
        boton.setId(mensaje);
        return boton;
    }
}
