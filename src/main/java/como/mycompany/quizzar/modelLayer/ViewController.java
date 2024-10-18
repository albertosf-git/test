package como.mycompany.quizzar.modelLayer;

import com.mycompany.quizzar.entity.Administrador;
import com.mycompany.quizzar.entity.Jugador;
import com.mycompany.quizzar.entity.Usuario;
import com.mycompany.quizzar.exception.QuizzarException;
import com.mycompany.quizzar.service.AdministradorService;
import com.mycompany.quizzar.service.JugadorService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ViewController {
	
    public static Connection getConnection() throws SQLException {
	return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "myquizzarpassword");
    }
	
    private final Connection connection;
    private final JugadorService jugadorService;
    private final AdministradorService administradorService;
	
    public ViewController() throws SQLException {
        this.connection = getConnection();
	this.administradorService = new AdministradorService(connection);
	this.jugadorService = new JugadorService(connection);
    }
	
    public void crearJugador(final Usuario usuario) throws SQLException, QuizzarException {
	jugadorService.crearJugador(usuario);
    }
	
    public void crearAdministrador(final Usuario usuario) throws SQLException, QuizzarException {
	administradorService.crearAdministrador(usuario);
    }
	
    public void eliminarJugador(final String nombre) throws SQLException {
	jugadorService.eliminarJugador(nombre);
    }
	
    public void eliminarAdministrador(final String nombre) throws SQLException {
	administradorService.eliminarAdministrador(nombre);
    }
	
    public List<Jugador> obtenerJugadores() throws SQLException {
	return jugadorService.obtenerUsuarios();
    }
	
    public List<Administrador> obtenerAdministradores() throws SQLException {
	return administradorService.obtenerAdministradores();
    }
}