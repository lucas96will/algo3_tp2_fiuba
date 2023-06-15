package edu.fiuba.algo3.modelo.Factory;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoIdentificadaException;

import java.util.HashMap;

public class DefensaFactory {

    private final HashMap<String, Defensa> tipoDeDefensa = new HashMap<>();

    public Defensa DefensaFactory(String nombreDefensa){

        crearEnemigos();
        Defensa defensa = tipoDeDefensa.get(nombreDefensa);

        if(defensa == null) {
            throw new DefensaNoIdentificadaException();
        }
        return defensa;
    }

    private void crearEnemigos (){
        this.tipoDeDefensa.put("Blanca", new Torre(10,1,3,new EstadoDefensaIncompleto(1),"Torre Blanca"));
        this.tipoDeDefensa.put("Plateada", new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada"));
    }
}