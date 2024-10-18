package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.AsignaturaDAO;
import com.mycompany.quizzar.dao.OpcionRespuestaDAO;
import com.mycompany.quizzar.dao.PreguntaDAO;
import com.mycompany.quizzar.entity.Pregunta;
import com.mycompany.quizzar.exception.AsignaturaNoExiste;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PreguntaService {
    private final OpcionRespuestaDAO opcionesDAO;
    private final AsignaturaDAO asignaturaDAO;
    private final PreguntaDAO preguntaDAO;
	
    public PreguntaService(Connection connection) {
	this.preguntaDAO = new PreguntaDAO(connection);
	this.opcionesDAO = new OpcionRespuestaDAO(connection);
	this.asignaturaDAO = new AsignaturaDAO(connection);
    }
	
    public void crearPregunta(Pregunta pregunta, List<String> opciones) throws SQLException, QuizzarException {
	if(asignaturaDAO.obtenerAsignaturaPorNombre(pregunta.getAsignatura()) == null) {
            throw new AsignaturaNoExiste();
	}
	int id = preguntaDAO.insertarPregunta(pregunta);
	int n = 0;
	for(String op : opciones) {
            opcionesDAO.insertarOpcionRespuesta(n, id, op);
            n++;
	}
    }
	
    public List<Pregunta> obtenerPreguntas() throws SQLException {
	List<Pregunta> preguntas = preguntaDAO.obtenerPreguntas();
	for(Pregunta p : preguntas) {
            p.setOpciones(opcionesDAO.obtenerOpciones(p.getId()));
	}
	return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntas(String asignatura) throws SQLException {
	List<Pregunta> preguntas = preguntaDAO.obtenerPreguntasPorAsignatura(asignatura);
	for(Pregunta p : preguntas) {
            p.setOpciones(opcionesDAO.obtenerOpciones(p.getId()));
        }
        return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntas(int dificultad) throws SQLException {
	List<Pregunta> preguntas = preguntaDAO.obtenerPreguntasPorDificultad(dificultad);
	for(Pregunta p : preguntas) {
            p.setOpciones(opcionesDAO.obtenerOpciones(p.getId()));
	}
	return preguntas;
    }
	
    public List<Pregunta> obtenerPreguntas(String asignatura, int dificultad) throws SQLException {
        List<Pregunta> preguntas = preguntaDAO.obtenerPreguntasPorAsignaturayDificultad(asignatura, dificultad);
	for(Pregunta p : preguntas) {
            p.setOpciones(opcionesDAO.obtenerOpciones(p.getId()));
	}
	return preguntas;
    }
	
    public void eliminarPregunta(int id) throws SQLException {
	preguntaDAO.eliminarPregunta(id);
    }
	
    public void modificarPregunta(int id, Pregunta nuevaPregunta) throws SQLException {
	preguntaDAO.modificarPregunta(0, nuevaPregunta);
    }
}