package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    private final Connection connection;
	
    public AdministradorDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarAdministrador(Administrador administrador) throws SQLException {
        String query = "INSERT INTO Administrador (nombre, tipo) VALUES (?, ?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, administrador.getNombre());
            stmt.setBoolean(2, administrador.isEsSupremo());
            stmt.executeUpdate();
	}
    }
	
    public Administrador obtenerAdministradorPorNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM Administrador WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    return new Administrador(rs.getString("nombre"), rs.getBoolean("tipo"));
		}
            }
        }
	return null;
    }
	
    public List<Administrador> obtenerAdministradores() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
	String query = "SELECT * FROM Administrador WHERE tipo = false";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    administradores.add(new Administrador(rs.getString("nombre"), false));
		}
            }
	}
	return administradores;
    }
}

