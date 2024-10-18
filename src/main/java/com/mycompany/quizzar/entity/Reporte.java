package com.mycompany.quizzar.entity;

import java.sql.Timestamp;

public class Reporte {
    private String contenido, nomJugador;
    private java.sql.Timestamp fecha;
	
    public Reporte(String contenido, String nomJugador, Timestamp fecha) {
        this.contenido = contenido;
	this.nomJugador = nomJugador;
	this.fecha = fecha;
    }

    public String getContenido() {
	return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNomJugador() {
	return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
	this.nomJugador = nomJugador;
    }

    public java.sql.Timestamp getFecha() {
	return fecha;
    }

    public void setFecha(java.sql.Timestamp fecha) {
	this.fecha = fecha;
    }
}

