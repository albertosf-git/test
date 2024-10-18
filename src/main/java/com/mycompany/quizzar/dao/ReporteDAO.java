package com.mycompany.quizzar.dao;

import com.mycompany.quizzar.entity.Reporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    private final Connection connection;

    public ReporteDAO(Connection connection) {
        this.connection = connection;
    }
	
    public void insertarReporte(Reporte reporte, int idPregunta) throws SQLException {
        String query = "INSERT INTO Reporte (contenido, fecha, idPregunta, nomJugador) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, reporte.getContenido());
            stmt.setTimestamp(2, reporte.getFecha());
            stmt.setInt(3, idPregunta);
            stmt.setString(4, reporte.getNomJugador());
            stmt.executeUpdate();
        }
    }
	
    public void eliminarReporte(String nomJugador, int idPregunta, java.sql.Timestamp fecha) throws SQLException {
	String query = "DELETE FROM Reporte WHERE nomJugador = ? AND idPregunta = ? AND fecha = ?";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomJugador);
            stmt.setInt(2, idPregunta);
            stmt.setTimestamp(3, fecha);
            stmt.executeUpdate();
	}
    }
	
    public List<Reporte> obtenerReportes() throws SQLException {
	List<Reporte> reportes = new ArrayList<>();
	String query = "SELECT * FROM REPORTE "
            + "ORDER BY fecha DESC";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
		while(rs.next()) {
                    reportes.add(new Reporte(rs.getString("contenido"), rs.getString("nomJugador"), rs.getTimestamp("fecha")));
		}
            }
	}
	return reportes;
    }
}