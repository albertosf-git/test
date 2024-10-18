package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Partida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    private final Connection connection;
	
    public PartidaDAO(Connection connection) {
	this.connection = connection;
    }
	
    public void insertarPartida(Partida partida) throws SQLException {
	String query = "INSERT INTO Partida (nomJugador, nomAsignat, puntos, tiempo, fecha) VALUES (?, ?, ?, ?, ?)";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, partida.getNomJugador());
            stmt.setString(2, partida.getNomAsignatura());
            stmt.setInt(3, partida.getPuntos());
            stmt.setInt(4, partida.getTiempo());
            stmt.setTimestamp(5, partida.getFecha());
            stmt.executeUpdate();
	}
    }
	
    public Partida obtenerPartida(String nomJugador, String nomAsignatura, java.sql.Timestamp fecha) throws SQLException {
	String query = "SELECT * FROM Partida WHERE nomJugador = ? AND nomAsignat = ? AND fecha = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomJugador);
            stmt.setString(2, nomAsignatura);
            stmt.setTimestamp(3, fecha);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return new Partida(rs.getString("nomJugador"), rs.getString("nomAsignat"), rs.getInt("puntos"), rs.getInt("tiempo"), rs.getTimestamp("fecha"));
		}
            }
	}
	return null;
    }
	
    public Partida obtenerMejorPartida(String nomJugador, String nomAsignatura) throws SQLException {
	String query = "SELECT * FROM Partida WHERE nomJugador = ? AND nomAsignat = ? "
            + "ORDER BY puntos DESC "
            + "LIMIT 1";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomJugador);
            stmt.setString(2, nomAsignatura);
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    return new Partida(rs.getString("nomJugador"), rs.getString("nomAsignat"), rs.getInt("puntos"), rs.getInt("tiempo"), rs.getTimestamp("fecha"));
		}
            }
        }
	return null;
    }
	
    public Partida obtenerUltimaPartida(String nomJugador, String nomAsignatura) throws SQLException {
	String query = "SELECT * FROM Partida WHERE nomJugador = ? AND nomAsignat = ? "
            + "ORDER BY fecha DESC "
            + "LIMIT 1";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomJugador);
            stmt.setString(2, nomAsignatura);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return new Partida(rs.getString("nomJugador"), rs.getString("nomAsignat"), rs.getInt("puntos"), rs.getInt("tiempo"), rs.getTimestamp("fecha"));
                }
            }
        }
	return null;
    }
	
    public List<Partida> obtenerPartidasDeJugador(String nomJugador) throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        String query = "SELECT * FROM Partida WHERE nomJugador = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomJugador);
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    partidas.add(new Partida(rs.getString("nomJugador"), rs.getString("nomAsignat"), rs.getInt("puntos"), rs.getInt("tiempo"), rs.getTimestamp("fecha")));
		}
            }
	}
	return partidas;
    }
	
    public List<Partida> obtenerMejoresPartidasPorAsignatura(String nomAsignat) throws SQLException {
	List<Partida> partidas = new ArrayList<>();
	String query = "SELECT * FROM Partida WHERE nomAsignat = ? "
            + "ORDER BY puntos DEC "
            + "LIMIT 10";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomAsignat);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    partidas.add(new Partida(rs.getString("nomJugador"), rs.getString("nomAsignat"), rs.getInt("puntos"), rs.getInt("tiempo"), rs.getTimestamp("fecha")));
		}
            }
        }
	return partidas;
    }
}