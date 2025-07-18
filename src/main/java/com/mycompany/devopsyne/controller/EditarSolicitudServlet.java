
package com.mycompany.devopsyne.controller;

import com.mycompany.devopsyne.DAO.SolicitudDAO;
import com.mycompany.devopsyne.DAO.SolicitudMaterialDAO;
import com.mycompany.devopsyne.model.EstadoSolicitud;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/editar-solicitud")
public class EditarSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Recuperar parámetros del formulario
            Long solicitudId = Long.valueOf(request.getParameter("solicitudId"));
            Long solicitanteId = Long.valueOf(request.getParameter("solicitanteId"));
            String fechaStr = request.getParameter("fecha");
            String estadoStr = request.getParameter("estado");

            String[] materialesIds = request.getParameterValues("materialId");
            String[] cantidadesStr = request.getParameterValues("cantidad");

            // 2. Convertir fecha y estado
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);
            EstadoSolicitud estado = EstadoSolicitud.valueOf(estadoStr);

            // 3. Crear instancia de solicitud
            Solicitante solicitante = new Solicitante();
            solicitante.setId(solicitanteId);

            Solicitud solicitud = new Solicitud();
            solicitud.setId(solicitudId);
            solicitud.setSolicitante(solicitante);
            solicitud.setFecha(fecha);
            solicitud.setEstado(estado);

            // 4. Actualizar solicitud en DB
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            solicitudDAO.update(solicitud);  // solo tabla solicitud

            // 5. Eliminar materiales anteriores
            SolicitudMaterialDAO solicitudMaterialDAO = new SolicitudMaterialDAO();
            solicitudMaterialDAO.deleteBySolicitud(solicitudId);

            // 6. Insertar materiales nuevos
            if (materialesIds != null && cantidadesStr != null) {
                for (int i = 0; i < materialesIds.length; i++) {
                    Long materialId = Long.valueOf(materialesIds[i]);
                    int cantidad = Integer.parseInt(cantidadesStr[i]);

                    Material material = new Material();
                    material.setId(materialId);

                    SolicitudMaterial sm = new SolicitudMaterial();

                    SolicitudMaterialId smId = new SolicitudMaterialId();
                    smId.setSolicitudId(solicitudId);
                    smId.setMaterialId(materialId);
                    sm.setId(smId);

                    sm.setSolicitud(solicitud);
                    sm.setMaterial(material);
                    sm.setCantidad(cantidad);

                    solicitudMaterialDAO.insert(sm);
                }
            }

            // 7. Redireccionar o notificar
            response.sendRedirect("ver-solicitudes");

        } catch (DAOException | IOException | NumberFormatException | ParseException e) {
            request.setAttribute("error", "Error al editar la solicitud: " + e.getMessage());
            request.getRequestDispatcher("editarSolicitud.jsp").forward(request, response);
        }
    }
}



