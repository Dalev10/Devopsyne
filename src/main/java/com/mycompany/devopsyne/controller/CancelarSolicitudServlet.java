
package com.mycompany.devopsyne.controller;


import com.mycompany.devopsyne.DAO.SolicitudDAO;
import com.mycompany.devopsyne.model.EstadoSolicitud;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.Solicitud;
import com.mycompany.devopsyne.model.Solicitante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

//Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/cancelar-solicitud")
public class CancelarSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Obtener ID de la solicitud desde el formulario
            Long solicitudId = Long.valueOf(request.getParameter("solicitudId"));

            // 2. Obtener el solicitante actual desde sesión 
            HttpSession session = request.getSession(false);
            Solicitante solicitante = (Solicitante) session.getAttribute("usuario");

            // 3. Crear instancia de la solicitud con el nuevo estado CANCELADA
            Solicitud solicitud = new Solicitud();
            solicitud.setId(solicitudId);
            solicitud.setEstado(EstadoSolicitud.CANCELADA);
            solicitud.setFecha(new Date()); 

            if (solicitante != null) {
                solicitud.setSolicitante(solicitante);
            }

            // 4. Actualizar solo el estado usando el DAO
            SolicitudDAO solicitudDAO = new SolicitudDAO();

            // Cargamos la solicitud desde la DB para conservar solicitante y fecha
            Solicitud existente = solicitudDAO.findById(solicitudId).orElse(null);
            if (existente != null) {
                existente.setEstado(EstadoSolicitud.CANCELADA);
                solicitudDAO.update(existente);
            }

            // 5. Redireccionar al listado
            response.sendRedirect("ver-solicitudes");

        } catch (DAOException | NumberFormatException e) {
            request.setAttribute("error", "Error al cancelar la solicitud: " + e.getMessage());
            request.getRequestDispatcher("verSolicitudes.jsp").forward(request, response);
        }
    }
}

