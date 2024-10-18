package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
    private final Connection connection;
	
    public JugadorDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarJugador(Jugador jugador) throws SQLException {
	String query = "INSERT INTO Jugador (nombre) VALUES (?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, jugador.getNombre());
            stmt.executeUpdate();
        }
    }
	
    public Jugador obtenerJugadorPorNombre(String nombre) throws SQLException {
	String query = "SELECT * FROM Jugador WHERE nombre = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    return new Jugador(rs.getString("nombre"));
		}
            }
	}
	return null;
    }
	
    public List<Jugador> obtenerJugadores() throws SQLException {
	List<Jugador> jugadores = new ArrayList<>();
	String query = "SELECT * FROM Jugador";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    jugadores.add(new Jugador(rs.getString("nombre")));
                }
            }
	}
	return jugadores;
    }
}