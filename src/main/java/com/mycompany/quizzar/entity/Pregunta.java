package com.mycompany.quizzar.entity;

import java.sql.Timestamp;
import java.util.List;

public class Pregunta {
    private int tiempo, dificultad;
    private int id;
    private String titulo, respuesta, imagenUrl, asignatura;
    private java.sql.Timestamp fechaUltMod;
    private List<String> opciones;
	
    public Pregunta(int id, int tiempo, int dificultad, String asignatura, String titulo, String respuesta,
			String imagenUrl, Timestamp fechaUltMod) {
	this.id = id;
	this.tiempo = tiempo;
	this.dificultad = dificultad;
	this.asignatura = asignatura;
	this.titulo = titulo;
	this.respuesta = respuesta;
	this.imagenUrl = imagenUrl;
	this.fechaUltMod = fechaUltMod;
    }
	
    public Pregunta(int tiempo, int dificultad, String asignatura, String titulo, String respuesta,
			String imagenUrl, Timestamp fechaUltMod) {
	this.tiempo = tiempo;
	this.dificultad = dificultad;
	this.asignatura = asignatura;
	this.titulo = titulo;
	this.respuesta = respuesta;
	this.imagenUrl = imagenUrl;
	this.fechaUltMod = fechaUltMod;
    }
	
    public int getId() {
	return id;
    }
	
    public int getTiempo() {
	return tiempo;
    }
	
    public void setTiempo(int tiempo) {
    	this.tiempo = tiempo;
    }
	
    public int getDificultad() {
	return dificultad;
    }
	
    public void setDificultad(int dificultad) {
    	this.dificultad = dificultad;
    }
	
    public String getAsignatura() {
	return asignatura;
    }
	
    public void setAsignatura(String asignatura) {
	this.asignatura = asignatura;
    }
	
    public String getTitulo() {
	return titulo;
    }
	
    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }
	
    public String getRespuesta() {
	return respuesta;
    }
	
    public void setRespuesta(String respuesta) {
	this.respuesta = respuesta;
    }
	
    public String getImagenUrl() {
	return imagenUrl;
    }
	
    public void setImagenUrl(String imagenUrl) {
	this.imagenUrl = imagenUrl;
    }
	
    public java.sql.Timestamp getFechaUltMod() {
	return fechaUltMod;
    }
	
    public void setFechaUltMod(java.sql.Timestamp fechaUltMod) {
	this.fechaUltMod = fechaUltMod;
    }
	
    public List<String> getOpciones() {
	return opciones;
    }
	
    public void setOpciones(List<String> opciones) {
	this.opciones = opciones;
    }
}

