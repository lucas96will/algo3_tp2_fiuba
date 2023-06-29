package edu.fiuba.algo3.view;

import java.util.HashMap;

public class Constantes {
    public static final String SONIDO_EFECTO_CLICK_GENERICO = "click_generico.mp3";
    public static final String RUTA_INFORMACION_JUEGO = "/pdf/informacionDelJuego.pdf";

    public static final HashMap<String, String> urlImagenesEnemigos;
    static {
        urlImagenesEnemigos = new HashMap<>();
        urlImagenesEnemigos.put("Hormiga", "/images/Hormiga.png");
        urlImagenesEnemigos.put("Araña", "/images/Arania.png");
        urlImagenesEnemigos. put("Topo", "/images/Topo.png");
        urlImagenesEnemigos.put("Lechuza", "/images/Buho.png");
    }
    private Constantes(){
    }
}
