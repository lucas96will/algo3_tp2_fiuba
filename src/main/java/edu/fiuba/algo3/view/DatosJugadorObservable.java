package edu.fiuba.algo3.view;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DatosJugadorObservable {
    StringProperty nombre;
    StringProperty vidaJugador;

    StringProperty credito;

    StringProperty turno;

    StringProperty cantDefensas;

    StringProperty cantEnemigos;

    public DatosJugadorObservable() {
        nombre = new SimpleStringProperty();
        vidaJugador = new SimpleStringProperty();
        credito = new SimpleStringProperty();
        turno = new SimpleStringProperty();
        cantDefensas = new SimpleStringProperty();
        cantEnemigos = new SimpleStringProperty();
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getVidaJugador() {
        return vidaJugador.get();
    }

    public StringProperty vidaJugadorProperty() {
        return vidaJugador;
    }

    public String getCredito() {
        return credito.get();
    }

    public StringProperty creditoProperty() {
        return credito;
    }

    public String getTurno() {
        return turno.get();
    }

    public StringProperty turnoProperty() {
        return turno;
    }

    public String getCantDefensas() {
        return cantDefensas.get();
    }

    public StringProperty cantDefensasProperty() {
        return cantDefensas;
    }

    public String getCantEnemigos() {
        return cantEnemigos.get();
    }

    public StringProperty cantEnemigosProperty() {
        return cantEnemigos;
    }

    public void setVidaJugador(String vidaJugador) {
        this.vidaJugador.set(vidaJugador);
    }

    public void setCredito(String credito) {
        this.credito.set(credito);
    }

    public void setTurno(String turno) {
        this.turno.set(turno);
    }

    public void setCantDefensas(String cantDefensas) {
        this.cantDefensas.set(cantDefensas);
    }

    public void setCantEnemigos(String cantEnemigos) {
        this.cantEnemigos.set(cantEnemigos);
    }

    public void actualizar() {
        setVidaJugador(String.valueOf(Jugador.getInstance().obtenerVidaJugador()));
        setCredito(String.valueOf(Jugador.getInstance().valorCreditos()));
        setCantDefensas(String.valueOf(Jugador.getInstance().obtenerDefensas().size()));
        setTurno(String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        setCantEnemigos(String.valueOf(Juego.getInstance().obtenerEnemigos().size()));
    }
}
