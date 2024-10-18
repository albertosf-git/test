package com.mycompany.quizzar.entity;

public class Asignatura {
    private String nombre;
	
    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
}
