
package com.mycompany.devopsyne.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Invalida la sesión del usuario y redirige al login.
 */

// Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false); 
        if (session != null) {
            session.invalidate();
        }

        // Redirige al login con parámetro opcional
        resp.sendRedirect(req.getContextPath() + "/login.jsp?logout=true");
    }
}

