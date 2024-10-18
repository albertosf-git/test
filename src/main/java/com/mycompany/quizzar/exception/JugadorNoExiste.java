package com.mycompany.quizzar.exception;

public class JugadorNoExiste extends QuizzarException {
    public JugadorNoExiste() {
        super("Jugador no existe");
    }
}