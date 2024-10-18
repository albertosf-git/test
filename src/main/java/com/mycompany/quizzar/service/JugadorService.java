package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.JugadorDAO;
import com.mycompany.quizzar.dao.UsuarioDAO;
import com.mycompany.quizzar.entity.Jugador;
import com.mycompany.quizzar.entity.Usuario;
import com.mycompany.quizzar.exception.CorreoYaUsado;
import com.mycompany.quizzar.exception.NombreYaUsado;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JugadorService {
    private final JugadorDAO jugadorDAO;
    private final UsuarioDAO usuarioDAO;
    
    public JugadorService(Connection connection) {
	this.jugadorDAO = new JugadorDAO(connection);
	this.usuarioDAO = new UsuarioDAO(connection);
    }
	
    public void crearJugador(Usuario usuario) throws SQLException, QuizzarException {
	if(usuarioDAO.obtenerUsuarioPorNombre(usuario.getNombre()) != null) {
            throw new NombreYaUsado();
	}
		
	if(usuarioDAO.obtenerUsuarioPorCorreo(usuario.getCorreo()) != null) {
            throw new CorreoYaUsado();
        }
		
	usuarioDAO.insertarUsuario(usuario);
	jugadorDAO.insertarJugador(new Jugador(usuario.getNombre()));
    }
	
    public List<Jugador> obtenerUsuarios() throws SQLException {
	return jugadorDAO.obtenerJugadores();
    }
	
    public void eliminarJugador(String nombre) throws SQLException {
	if(jugadorDAO.obtenerJugadorPorNombre(nombre) != null) {
            usuarioDAO.eliminarUsuario(nombre);
        }
    }
}

