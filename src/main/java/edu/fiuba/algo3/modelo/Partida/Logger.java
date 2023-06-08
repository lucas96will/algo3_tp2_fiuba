package edu.fiuba.algo3.modelo.Partida;

public final class Logger {
    private static Logger logger;

    private Logger() {}

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void logExitoso(String mensaje) {
        System.out.println("\u001B[32m" + mensaje + "\u001B[0m");
    }

    public void logError(String mensaje) {
        System.out.println("\u001B[31m" + mensaje + "\u001B[0m");
    }

    public void logEstado(String mensaje){ System.out.println(""+mensaje+"");}
}


