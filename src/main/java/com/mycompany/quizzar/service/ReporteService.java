package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.JugadorDAO;
import com.mycompany.quizzar.dao.PreguntaDAO;
import com.mycompany.quizzar.dao.ReporteDAO;
import com.mycompany.quizzar.entity.Reporte;
import com.mycompany.quizzar.exception.JugadorNoExiste;
import com.mycompany.quizzar.exception.PreguntaNoExiste;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReporteService {
    private final ReporteDAO reporteDAO;
    private final JugadorDAO jugadorDAO;
    private final PreguntaDAO preguntaDAO;
	
    public ReporteService(Connection connection) {
        super();
        this.reporteDAO = new ReporteDAO(connection);
        this.jugadorDAO = new JugadorDAO(connection);
        this.preguntaDAO = new PreguntaDAO(connection);
    }
	
    public void crearReporte(Reporte reporte, int idPregunta) throws SQLException, QuizzarException {
	if(jugadorDAO.obtenerJugadorPorNombre(reporte.getNomJugador()) == null) {
            throw new JugadorNoExiste();
	}
		
	if(preguntaDAO.obtenerPreguntaPorId(idPregunta) == null) {
            throw new PreguntaNoExiste();
	}
		
	reporteDAO.insertarReporte(reporte, idPregunta);
    }
	
    public void eliminarReporte(String nomJugador, int idPregunta, java.sql.Timestamp fecha) throws SQLException{
	if(preguntaDAO.obtenerPreguntaPorId(idPregunta) != null && jugadorDAO.obtenerJugadorPorNombre(nomJugador) != null) {
            reporteDAO.eliminarReporte(nomJugador, idPregunta, fecha);
        }
    }
	
    public List<Reporte> obtenerReportes() throws SQLException {
        return reporteDAO.obtenerReportes();
    }
}