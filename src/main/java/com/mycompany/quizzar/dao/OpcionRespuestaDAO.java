package com.mycompany.quizzar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpcionRespuestaDAO {
    private final Connection connection;
	
    public OpcionRespuestaDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarOpcionRespuesta(int id, int idPregunta, String contenido) throws SQLException {
	String query = "INSERT INTO OpcionRespuesta (id, contenido, idPregunta) VALUES (?, ?, ?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(3, idPregunta);
            stmt.setString(2, contenido);
            stmt.executeUpdate();
	}
    }
	
    public List<String> obtenerOpciones(int idPregunta) throws SQLException {
        List<String> opciones = new ArrayList<>();
	String query = "SELECT contenido FROM OpcionRespuesta WHERE idPregunta = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPregunta);
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    opciones.add(rs.getString("contenido"));
		}
            }
	}
	return opciones;
    }
	
    public void modificarOpcionRespuesta(int id, int idPregunta, String nuevoContenido) throws SQLException {
	String query = "UPDATE OpcionRespuesta SET contenido = ? WHERE id = ? AND idPregunta = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevoContenido);
            stmt.setInt(2, id);
            stmt.setInt(3, idPregunta);
            stmt.executeUpdate();
	}
    }
}


