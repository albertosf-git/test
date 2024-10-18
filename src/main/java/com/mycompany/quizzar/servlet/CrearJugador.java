package com.mycompany.quizzar.servlet;

import com.mycompany.quizzar.entity.Usuario;
import com.mycompany.quizzar.exception.QuizzarException;
import como.mycompany.quizzar.modelLayer.ViewController;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CrearJugador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasegna = request.getParameter("contrasegna");
        
        String error = "";
        
        try {
            ViewController mvc = new ViewController();
            mvc.crearJugador(new Usuario(nombre, correo, contrasegna));
        } catch(QuizzarException e) {
            error = e.getMessage(); 
        } catch(SQLException e) {
            error = "Error al realizar la operación";
        }
        
        if(!error.isEmpty()) {
            request.setAttribute("error", error);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("crearCuenta.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la creación de nuevas cuentas de jugadores.";
    }

}
