package com.mycompany.quizzar.entity;

import java.sql.Timestamp;

public class Partida {
    private String nomJugador, nomAsignatura;
    private int puntos, tiempo;
    private java.sql.Timestamp fecha;
	
    public Partida(String nomJugador, String nomAsignatura, int puntos, int tiempo, Timestamp fecha) {
	this.nomJugador = nomJugador;
	this.nomAsignatura = nomAsignatura;
	this.puntos = puntos;
	this.tiempo = tiempo;
	this.fecha = fecha;
    }

    public String getNomJugador() {
	return nomJugador;
    }
	
    public void setNomJugador(String nomJugador) {
	this.nomJugador = nomJugador;
    }
	
    public String getNomAsignatura() {
	return nomAsignatura;
    }
	
    public void setNomAsignatura(String nomAsignatura) {
    	this.nomAsignatura = nomAsignatura;
    }
	
    public int getPuntos() {
	return puntos;
    }
	
    public void setPuntos(int puntos) {
	this.puntos = puntos;
    }
	
    public int getTiempo() {
	return tiempo;
    }
    
    public void setTiempo(int tiempo) {
	this.tiempo = tiempo;
    }
	
    public java.sql.Timestamp getFecha() {
	return fecha;
    }
	
    public void setFecha(java.sql.Timestamp fecha) {
	this.fecha = fecha;
    }
}

