package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {
    private final Connection connection;
	
    public PreguntaDAO(Connection connection) {
        this.connection = connection;
    }
	
    public int insertarPregunta(Pregunta pregunta) throws SQLException {
        int generatedId = -1;
	String query = "INSERT INTO Pregunta (nomAsignat, respuesta, titulo, fechUltMod, dificultad, tiempo, imagen) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pregunta.getAsignatura());
            stmt.setString(2, pregunta.getRespuesta());
            stmt.setString(3, pregunta.getTitulo());
            stmt.setTimestamp(4, pregunta.getFechaUltMod());
            stmt.setInt(5, pregunta.getDificultad());
            stmt.setInt(6, pregunta.getTiempo());
            stmt.setString(7, pregunta.getImagenUrl());
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    generatedId = rs.getInt(1);
		}
            }
	}
	return generatedId;
    }

    public Pregunta obtenerPreguntaPorId(int id) throws SQLException {
	String query = "SELECT * FROM Pregunta WHERE id = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
		if(rs.next()) {
                    return new Pregunta(rs.getInt("id"), rs.getInt("tiempo"), rs.getInt("dificultad"), rs.getString("nomAsignat"),
                        rs.getString("titulo"), rs.getString("respuesta"), rs.getString("imagen"), rs.getTimestamp("fechUltMod"));
                }
            }
	}
	return null;
    }
	
    public List<Pregunta> obtenerPreguntas() throws SQLException {
	List<Pregunta> preguntas = new ArrayList<>();
	String query = "SELECT * FROM Pregunta";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    preguntas.add(new Pregunta(rs.getInt("id"), rs.getInt("tiempo"), rs.getInt("dificultad"), rs.getString("nomAsignat"),
                        rs.getString("titulo"), rs.getString("respuesta"), rs.getString("imagen"), rs.getTimestamp("fechUltMod")));
		}
            }
	}
	return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntasPorDificultad(int dificultad) throws SQLException {
	List<Pregunta> preguntas = new ArrayList<>();
	String query = "SELECT * FROM Pregunta WHERE dificultad = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dificultad);
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    preguntas.add(new Pregunta(rs.getInt("id"), rs.getInt("tiempo"), rs.getInt("dificultad"), rs.getString("nomAsignat"),
                        rs.getString("titulo"), rs.getString("respuesta"), rs.getString("imagen"), rs.getTimestamp("fechUltMod")));
                }
            }
	}
	return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntasPorAsignatura(String asignatura) throws SQLException {
	List<Pregunta> preguntas = new ArrayList<>();
	String query = "SELECT * FROM Pregunta WHERE nomAsignat ILIKE ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, asignatura + '%');
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    preguntas.add(new Pregunta(rs.getInt("id"), rs.getInt("tiempo"), rs.getInt("dificultad"), rs.getString("nomAsignat"),
                        rs.getString("titulo"), rs.getString("respuesta"), rs.getString("imagen"), rs.getTimestamp("fechUltMod")));
		}
            }
	}
	return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntasPorAsignaturayDificultad(String asignatura, int dificultad) throws SQLException {
	List<Pregunta> preguntas = new ArrayList<>();
	String query = "SELECT * FROM Pregunta WHERE nomAsignat ILIKE ? AND dificultad = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, asignatura + '%');
            stmt.setInt(2, dificultad);
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    preguntas.add(new Pregunta(rs.getInt("id"), rs.getInt("tiempo"), rs.getInt("dificultad"), rs.getString("nomAsignat"),
                    rs.getString("titulo"), rs.getString("respuesta"), rs.getString("imagen"), rs.getTimestamp("fechUltMod")));
		}
            }
	}
	return preguntas;
    }
	
    public void eliminarPregunta(int id) throws SQLException {
	String query = "DELETE FROM Pregunta WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
	
    public void modificarPregunta(int id, Pregunta nuevaPregunta) throws SQLException {
	String query = "UPDATE Pregunta SET nomAsignat = ?, respuesta = ?, titulo = ?, fechUltMod = ?, dificultad = ?, tiempo = ?, imagen = ? WHERE id = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevaPregunta.getAsignatura());
            stmt.setString(2, nuevaPregunta.getRespuesta());
            stmt.setString(3, nuevaPregunta.getTitulo());
            stmt.setTimestamp(4, nuevaPregunta.getFechaUltMod());
            stmt.setInt(5, nuevaPregunta.getDificultad());
            stmt.setInt(6, nuevaPregunta.getTiempo());
            stmt.setString(7, nuevaPregunta.getImagenUrl());
            stmt.executeUpdate();
        }
    }	
}