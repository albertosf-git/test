package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Connection connection;
	
    public UsuarioDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarUsuario(Usuario usuario) throws SQLException {
	String query = "INSERT INTO Usuario (nombre, correo, contrasegna) VALUES (?, ?, ?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasegna());
            stmt.executeUpdate();
        }
    }
	
    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
	String query = "SELECT * FROM Usuario WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return new Usuario(
                        rs.getString("nombre"),
			rs.getString("correo"),
			rs.getString("contrasegna")
                    );
		}
            }
        }
	return null;
    }
	
    public Usuario obtenerUsuarioPorCorreo(String correo) throws SQLException {
	String query = "SELECT * FROM Usuario WHERE correo = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return new Usuario(
                        rs.getString("nombre"),
			rs.getString("correo"),
                        rs.getString("contrasegna")
                    );
		}
            }
	}
	return null;
    }
	
    public void eliminarUsuario(String nombre) throws SQLException {
	String query = "DELETE FROM Usuario WHERE nombre = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
	}
    }
}