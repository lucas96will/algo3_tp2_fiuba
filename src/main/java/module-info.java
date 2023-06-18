module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.modelo.Parcela.Pasarela;
    exports edu.fiuba.algo3.modelo.Enemigo;
    exports edu.fiuba.algo3.modelo.Defensa;
    exports edu.fiuba.algo3.modelo.Parcela.Construible;
    exports edu.fiuba.algo3.modelo.Partida;
    exports edu.fiuba.algo3.modelo.Parcela;
    exports edu.fiuba.algo3.modelo.Factory;
    exports edu.fiuba.algo3.modelo.Mapa;
    exports edu.fiuba.algo3.modelo.Jugador;
    exports edu.fiuba.algo3.modelo.Cargador;
    exports edu.fiuba.algo3.modelo.Enemigo.Movimiento;
    exports edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;
}