package com.mycompany.quizzar.exception;

public class AsignaturaNoExiste extends QuizzarException {
    public AsignaturaNoExiste() {
        super("Asignatura no existe");
    }
}