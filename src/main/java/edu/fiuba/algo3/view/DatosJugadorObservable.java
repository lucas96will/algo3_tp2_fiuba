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

    StringProperty mensaje;

    StringProperty cantHormiga;
    StringProperty cantArania;
    StringProperty cantTopo;
    StringProperty cantLechuza;


    public DatosJugadorObservable() {
        nombre = new SimpleStringProperty();
        vidaJugador = new SimpleStringProperty();
        credito = new SimpleStringProperty();
        turno = new SimpleStringProperty();
        cantDefensas = new SimpleStringProperty();
        mensaje = new SimpleStringProperty();
        cantHormiga = new SimpleStringProperty();
        cantArania = new SimpleStringProperty();
        cantTopo = new SimpleStringProperty();
        cantLechuza = new SimpleStringProperty();
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty vidaJugadorProperty() {
        return vidaJugador;
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

    public StringProperty cantDefensasProperty() {
        return cantDefensas;
    }

    public StringProperty mensajeProperty() {
        return mensaje;
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
    public String getHormiga() {
        return cantHormiga.get();
    }
    public StringProperty cantHormigaProperty() {
        return cantHormiga;
    }
    public void setCantHormiga(String cantHormiga) {this.cantHormiga.set(cantHormiga);}
    public String getArania() {
        return cantArania.get();
    }
    public StringProperty cantAraniaProperty() {
        return cantArania;
    }
    public void setCantArania(String cantArania) {this.cantArania.set(cantArania);}
    public String getLechuza() {
        return cantLechuza.get();
    }
    public StringProperty cantLechuzaProperty() {
        return cantLechuza;
    }
    public void setCantLechuza(String cantLechuza) {this.cantLechuza.set(cantLechuza);}
    public String getTopo() {
        return cantTopo.get();
    }
    public StringProperty cantTopoProperty() {
        return cantTopo;
    }
    public void setCantTopo(String cantTopo) {this.cantTopo.set(cantTopo);}

    public void actualizar() {
        setVidaJugador(String.valueOf(Jugador.getInstance().obtenerVidaJugador()));
        setCredito(String.valueOf(Jugador.getInstance().valorCreditos()));
        setCantDefensas(String.valueOf(Jugador.getInstance().obtenerDefensas().size()));
        setTurno(String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        setCantHormiga(String.valueOf((int) Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Hormiga")).count()));
        setCantArania(String.valueOf((int) Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Arania")).count()));
        setCantTopo(String.valueOf((int) Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Topo")).count()));
        setCantLechuza(String.valueOf((int) Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Lechuza")).count()));
    }
}
