package com.mycompany.quizzar.entity;

public class Usuario {
    private String nombre;
    private String correo;
    private String contrasegna;
	
    public Usuario(String nombre, String correo, String contrasegna) {
	this.nombre = nombre;
	this.correo = correo;
	this.contrasegna = contrasegna;
    }
	
    public String getNombre() {
	return nombre;
    }
	
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
	
    public String getCorreo() {
	return correo;
    }
	
    public void setCorreo(String correo) {
	this.correo = correo;
    }
	
    public String getContrasegna() {
	return contrasegna;
    }
	
    public void setContrasegna(String contrasegna) {
	this.contrasegna = contrasegna;
    }	
}