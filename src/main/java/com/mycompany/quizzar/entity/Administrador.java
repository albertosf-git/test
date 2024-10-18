package com.mycompany.quizzar.entity;

public class Administrador {
    private String nombre;
    private boolean esSupremo;
	
    public Administrador(String nombre, boolean esSupremo) {
        this.nombre = nombre;
	this.esSupremo = esSupremo;
    }
	
    public String getNombre() {
	return nombre;
    }
    
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
    
    public boolean isEsSupremo() {
	return esSupremo;
    }
    
    public void setEsSupremo(boolean esSupremo) {
	this.esSupremo = esSupremo;
    }
}

