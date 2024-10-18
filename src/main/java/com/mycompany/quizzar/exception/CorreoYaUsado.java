package com.mycompany.quizzar.exception;

public class CorreoYaUsado extends QuizzarException {
    public CorreoYaUsado() {
        super("Correo ya usado");
    }
}