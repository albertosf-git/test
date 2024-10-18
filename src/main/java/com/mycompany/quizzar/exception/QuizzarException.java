package com.mycompany.quizzar.exception;

public class QuizzarException extends Exception {
    public QuizzarException(String error) {
        super("Error: " + error);
    }
}
