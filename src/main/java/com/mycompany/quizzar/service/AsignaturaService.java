package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.AsignaturaDAO;
import com.mycompany.quizzar.entity.Asignatura;
import com.mycompany.quizzar.exception.NombreYaUsado;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AsignaturaService {
    private final AsignaturaDAO asignaturaDAO;
	
    public AsignaturaService(Connection connection) {
	this.asignaturaDAO = new AsignaturaDAO(connection);
    }
	
    public void crearAsignatura(Asignatura asignatura) throws SQLException, QuizzarException {
	if(asignaturaDAO.obtenerAsignaturaPorNombre(asignatura.getNombre()) != null) {
            throw new NombreYaUsado();
	}
	asignaturaDAO.insertarAsignatura(asignatura);
    }
	
    public List<Asignatura> obtenerAsignaturas() throws SQLException {
	return asignaturaDAO.obtenerAsignaturas();
    }
	
    public void eliminarAsignatura(String nombre) throws SQLException {
	if(asignaturaDAO.obtenerAsignaturaPorNombre(nombre) != null) {
            asignaturaDAO.eliminarAsignatura(nombre);
        }
    }

    public void modificarAsignatura(String nombre, Asignatura nuevaAsignatura) throws SQLException, QuizzarException {
	if(asignaturaDAO.obtenerAsignaturaPorNombre(nombre) != null) {
            if(asignaturaDAO.obtenerAsignaturaPorNombre(nuevaAsignatura.getNombre()) == null) {
                asignaturaDAO.modificarAsignatura(nombre, nuevaAsignatura);
            } else {
                throw new NombreYaUsado();
            }
	}
    }
}