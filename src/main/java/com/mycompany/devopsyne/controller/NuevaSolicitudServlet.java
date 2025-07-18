
package com.mycompany.devopsyne.controller;

import com.mycompany.devopsyne.DAO.MaterialDAO;
import com.mycompany.devopsyne.DAO.SolicitanteDAO;
import com.mycompany.devopsyne.DAO.SolicitudDAO;
import com.mycompany.devopsyne.DAO.SolicitudMaterialDAO;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Autor: Diego Alejandro Vergara Ruiz

@WebServlet("/nueva-solicitud")
public class NuevaSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // Obtener parámetros del formulario
            String solicitanteIdStr = request.getParameter("solicitante");
            String fechaStr = request.getParameter("fecha"); // Formato esperado: "yyyy-MM-dd"
            String[] materialesId = request.getParameterValues("material_id");
            String[] cantidadesStr = request.getParameterValues("cantidad");

            // Validaciones básicas
            if (solicitanteIdStr == null || fechaStr == null || materialesId == null || cantidadesStr == null) {
                throw new IllegalArgumentException("Faltan parámetros obligatorios.");
            }

            Long solicitanteId = Long.valueOf(solicitanteIdStr);

            // Parsear la fecha
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);

            // Crear entidad Solicitante
            SolicitanteDAO solicitanteDAO = new SolicitanteDAO();
            Solicitante solicitante = solicitanteDAO.findById(solicitanteId)
                .orElseThrow(() -> new DAOException("El solicitante no existe."));


            // Crear la solicitud
            Solicitud solicitud = new Solicitud();
            solicitud.setSolicitante(solicitante);
            solicitud.setFecha(fecha);
            solicitud.setEstado(EstadoSolicitud.CREADA);

            SolicitudDAO solicitudDAO = new SolicitudDAO();
            solicitudDAO.insert(solicitud); 

            // Construir la lista de detalles de materiales
            List<SolicitudMaterial> detalles = new ArrayList<>();
            MaterialDAO materialDAO = new MaterialDAO();

            for (int i = 0; i < materialesId.length; i++) {
                Long materialId = Long.valueOf(materialesId[i]);
                int cantidad = Integer.parseInt(cantidadesStr[i]);

                // Buscar material y validar existencia
                Material material = materialDAO.findById(materialId)
                    .orElseThrow(() -> new DAOException("El material con ID " + materialId + " no existe."));


                // Crear instancia de relación
                SolicitudMaterial detalle = new SolicitudMaterial();
                detalle.setSolicitud(solicitud);
                detalle.setMaterial(material);
                detalle.setCantidad(cantidad);

                // Establecer ID compuesto
                SolicitudMaterialId detalleId = new SolicitudMaterialId(solicitud.getId(), material.getId());
                detalle.setId(detalleId);

                detalles.add(detalle);
            }

            // Insertar detalles en base de datos
            SolicitudMaterialDAO detalleDAO = new SolicitudMaterialDAO();
            for (SolicitudMaterial detalle : detalles) {
                detalleDAO.insert(detalle);
            }

            // Redireccionar al resumen o lista de solicitudes
            response.sendRedirect("lista-solicitudes.jsp");

        } catch (DAOException e) {
            request.setAttribute("error", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (NumberFormatException | ParseException e) {
            request.setAttribute("error", "Formato inválido en los datos: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Datos faltantes o inválidos: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
