package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.AsignaturaDAO;
import com.mycompany.quizzar.dao.JugadorDAO;
import com.mycompany.quizzar.dao.PartidaDAO;
import com.mycompany.quizzar.entity.Partida;
import com.mycompany.quizzar.exception.AsignaturaNoExiste;
import com.mycompany.quizzar.exception.JugadorNoExiste;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidaService {
    private final PartidaDAO partidaDAO;
    private final JugadorDAO jugadorDAO;
    private final AsignaturaDAO asignaturaDAO;
	
    public PartidaService(Connection connection) {
	super();
	this.partidaDAO = new PartidaDAO(connection);
	this.jugadorDAO = new JugadorDAO(connection);
	this.asignaturaDAO = new AsignaturaDAO(connection);
    }
	
    public void crearPartida(Partida partida) throws SQLException, QuizzarException {
	if(jugadorDAO.obtenerJugadorPorNombre(partida.getNomJugador()) == null) {
            throw new JugadorNoExiste();
	}
		
	if(asignaturaDAO.obtenerAsignaturaPorNombre(partida.getNomAsignatura()) == null) {
            throw new AsignaturaNoExiste();
	}
		
	partidaDAO.insertarPartida(partida);
    }
	
    public List<Partida> obtenerMejoresPartidasPorAsignatura(String nomAsignatura) throws SQLException {
	if(asignaturaDAO.obtenerAsignaturaPorNombre(nomAsignatura) != null) {
            return partidaDAO.obtenerMejoresPartidasPorAsignatura(nomAsignatura);
        }
	return new ArrayList<Partida>();
    }
	
    public Partida obtenerMejorPartida(String nomJugador, String nomAsignatura) throws SQLException {
	if(jugadorDAO.obtenerJugadorPorNombre(nomJugador) != null && asignaturaDAO.obtenerAsignaturaPorNombre(nomAsignatura) != null)  {
            return partidaDAO.obtenerMejorPartida(nomJugador, nomAsignatura);
	}
	System.out.println(asignaturaDAO.obtenerAsignaturaPorNombre(nomAsignatura) != null);
	return null;
    }
	
    public Partida obtenerUltimaPartida(String nomJugador, String nomAsignatura) throws SQLException {
	if(jugadorDAO.obtenerJugadorPorNombre(nomJugador) != null && asignaturaDAO.obtenerAsignaturaPorNombre(nomAsignatura) != null)  {
            return partidaDAO.obtenerUltimaPartida(nomJugador, nomAsignatura);
	}
	return null;
    }
}