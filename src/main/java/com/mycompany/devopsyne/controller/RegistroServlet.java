
package com.mycompany.devopsyne.controller;


import com.mycompany.devopsyne.DAO.SolicitanteDAO;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.service.RegistroException;
import com.mycompany.devopsyne.service.RegistroService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private RegistroService registroService;

    @Override
    public void init() {
        
        this.registroService = new RegistroService(new SolicitanteDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Muestra el formulario
        req.getRequestDispatcher("/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String identificacion = req.getParameter("identificacion");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String password = req.getParameter("password");

        // Crear bean solicitante
        Solicitante solicitante = new Solicitante();
        solicitante.setNombre(nombre);
        solicitante.setEmail(email);
        solicitante.setIdentificacion(identificacion);
        solicitante.setTelefono(telefono);
        solicitante.setDireccion(direccion);

        try {
            registroService.registrarNuevoSolicitante(solicitante, password);
            // registro OK → redirigir a login con mensaje
            req.getSession().setAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } catch (RegistroException e) {
            // Error en validación o BD → volver a formulario con error
            req.setAttribute("error", e.getMessage());
            req.setAttribute("solicitante", solicitante); // para repoblar campos
            req.getRequestDispatcher("/registro.jsp").forward(req, resp);
        }
    }
}
