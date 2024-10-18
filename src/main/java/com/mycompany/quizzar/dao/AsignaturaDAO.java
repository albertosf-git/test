package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Asignatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {
    private final Connection connection;
	
    public AsignaturaDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarAsignatura(Asignatura asignatura) throws SQLException {
	String query = "INSERT INTO Asignatura (nombre) VALUES (?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, asignatura.getNombre());
            stmt.executeUpdate();
	}
    }
	
    public Asignatura obtenerAsignaturaPorNombre(String nombre) throws SQLException {
	String query = "SELECT * FROM Asignatura WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    return new Asignatura(rs.getString("nombre"));
		}
            }
	}
	return null;
    }
	
    public List<Asignatura> obtenerAsignaturas() throws SQLException {
	List<Asignatura> asignaturas = new ArrayList<>();
	String query = "SELECT * FROM Asignatura";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    asignaturas.add(new Asignatura(rs.getString("nombre")));
		}
            }
	}
	return asignaturas;
    }
	
    public void eliminarAsignatura(String nombre) throws SQLException {
	String query = "DELETE FROM Asignatura WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
	}
    }
	
    public void modificarAsignatura(String nombre, Asignatura nuevaAsignatura) throws SQLException {
	String query = "UPDATE Asignatura SET nombre = ? WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevaAsignatura.getNombre());
            stmt.setString(2, nombre);
            stmt.executeUpdate();
	}
    }
}

