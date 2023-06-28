package edu.fiuba.algo3.view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextFieldNombre extends Label{

    public static void configurarTextFieldNombre(TextField textField, EventHandler controlador){
        textField.setOnKeyPressed(controlador);
    }

}
