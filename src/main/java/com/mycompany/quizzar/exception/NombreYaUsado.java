package com.mycompany.quizzar.exception;

public class NombreYaUsado extends QuizzarException {
    public NombreYaUsado() {
        super("Nombre ya usado");
    }
}