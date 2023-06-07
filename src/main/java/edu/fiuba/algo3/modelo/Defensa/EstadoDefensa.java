package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.List;

public interface EstadoDefensa {
    int atacar(List<Pasarela> pasarelasEnRango,int danio);

    void siguienteEstado(Defensa defensa);

    boolean puedeAtacar();
    EstadoDefensa reconstruir();

    public void registrarAtaque();
}
