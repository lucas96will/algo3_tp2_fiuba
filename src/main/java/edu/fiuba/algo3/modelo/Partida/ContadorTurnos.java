package edu.fiuba.algo3.modelo.Partida;

public class ContadorTurnos {
    private int turnoActual;
    private static ContadorTurnos contador = new ContadorTurnos();
    private ContadorTurnos() {
        turnoActual = 0;

    }

    public static ContadorTurnos obtenerContador() {
        return contador;
    }
    public int obtenerTurnoActual() {
        return turnoActual;
    }

    public void incrementarTurno() {
        turnoActual++;
    }

    public void resetear() {
        turnoActual = 0;
    }
}
