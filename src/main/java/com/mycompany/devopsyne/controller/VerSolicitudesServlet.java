
package com.mycompany.devopsyne.controller;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.DAO.SolicitudDAO;
import com.mycompany.devopsyne.model.Solicitud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/ver_solicitudes")
public class VerSolicitudesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SolicitudDAO solicitudDAO = new SolicitudDAO();

        try {
            List<Solicitud> solicitudes = solicitudDAO.findAll();
            request.setAttribute("solicitudes", solicitudes);

            // Redirigir al JSP que muestra la lista
            request.getRequestDispatcher("/Web Pages/ver_solicitudes.jsp").forward(request, response);

        } catch (DAOException e) {
            // Manejo de errores
            request.setAttribute("error", "Error al obtener las solicitudes: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}

