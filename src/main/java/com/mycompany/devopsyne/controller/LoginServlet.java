
package com.mycompany.devopsyne.controller;

import com.mycompany.devopsyne.DAO.SolicitanteDAO;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

//Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.loginService = new LoginService(new SolicitanteDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Reenvía al JSP de login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");

        if (email == null || plainPassword == null || email.isBlank() || plainPassword.isBlank()) {
            request.setAttribute("error", "Email y contraseña son obligatorios.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            Optional<Solicitante> usuario = loginService.autenticarYObtener(email, plainPassword);
            if (usuario.isPresent()) {
                // Login correcto, guardar en sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario.get());
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("error", "Credenciales incorrectas.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (DAOException | IOException | ServletException e) {
            request.setAttribute("error", "Ocurrió un error interno al autenticar.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

