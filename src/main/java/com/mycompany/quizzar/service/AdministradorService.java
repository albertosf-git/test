package com.mycompany.quizzar.service;

import com.mycompany.quizzar.dao.AdministradorDAO;
import com.mycompany.quizzar.dao.UsuarioDAO;
import com.mycompany.quizzar.entity.Administrador;
import com.mycompany.quizzar.entity.Usuario;
import com.mycompany.quizzar.exception.CorreoYaUsado;
import com.mycompany.quizzar.exception.NombreYaUsado;
import com.mycompany.quizzar.exception.QuizzarException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdministradorService {
    private final AdministradorDAO administradorDAO;
    private final UsuarioDAO usuarioDAO;
	
    public AdministradorService(Connection connection) {
        administradorDAO = new AdministradorDAO(connection);
	usuarioDAO = new UsuarioDAO(connection);
    }
	
    public void crearAdministrador(Usuario usuario) throws SQLException, QuizzarException {
	if(usuarioDAO.obtenerUsuarioPorNombre(usuario.getNombre()) != null) {
            throw new NombreYaUsado();
        }
        
	if(usuarioDAO.obtenerUsuarioPorCorreo(usuario.getCorreo()) != null) {
            throw new CorreoYaUsado();
	}
		
	usuarioDAO.insertarUsuario(usuario);
        administradorDAO.insertarAdministrador(new Administrador(usuario.getNombre(), false));
    }
	
    public List<Administrador> obtenerAdministradores() throws SQLException {
	return administradorDAO.obtenerAdministradores();
    }
	
    public void eliminarAdministrador(String nombre) throws SQLException {
	if(administradorDAO.obtenerAdministradorPorNombre(nombre) != null) {
            usuarioDAO.eliminarUsuario(nombre);
        }
    }	
}