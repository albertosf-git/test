package com.mycompany.quizzar.exception;

public class PreguntaNoExiste extends QuizzarException {
    public PreguntaNoExiste() {
        super("Pregunta no existe");
    }
}
