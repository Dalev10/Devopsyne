
package com.mycompany.devopsyne.service;

import com.mycompany.devopsyne.DAO.SolicitudDAO;
import com.mycompany.devopsyne.DAO.SolicitudMaterialDAO;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.Solicitud;
import com.mycompany.devopsyne.model.SolicitudMaterial;
import com.mycompany.devopsyne.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Autor: Diego Alejandro Vergara Ruiz

public class SolicitudService {

    private static final Logger LOG = Logger.getLogger(SolicitudService.class.getName());

    private final SolicitudDAO solicitudDAO;
    private final SolicitudMaterialDAO solicitudMaterialDAO;

    public SolicitudService(SolicitudDAO solicitudDAO, SolicitudMaterialDAO solicitudMaterialDAO) {
        this.solicitudDAO = solicitudDAO;
        this.solicitudMaterialDAO = solicitudMaterialDAO;
    }

    /**
     * Registra la solicitud (cabecera) y todos los materiales (detalle)
     * en una sola transacción.
     *
     * @param solicitud solicitud con solicitante, fecha, estado, etc.
     * @param materiales lista de detalles (SolicitudMaterial) a insertar
     * @throws DAOException si hay error en alguna parte del proceso
     */
    public void registrarSolicitudConMateriales(Solicitud solicitud, List<SolicitudMaterial> materiales) throws DAOException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // inicio explícito de transacción

            // Se usa la misma conexión para los DAO
            solicitudDAO.insert(solicitud, conn);

            // Ajustar en cada detalle el id de solicitud generado
            for (SolicitudMaterial detalle : materiales) {
                detalle.getId().setSolicitudId(solicitud.getId());
                solicitudMaterialDAO.insert(detalle, conn);
            }

            conn.commit();
            LOG.info("Solicitud y materiales registrados correctamente en una transacción.");

        } catch (SQLException | DAOException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                    LOG.log(Level.WARNING, "Transacci\u00f3n revertida debido a error: {0}", ex.getMessage());
                } catch (SQLException e) {
                    throw new DAOException("Error haciendo rollback", e);
                }
            }
            throw new DAOException("No se pudo registrar la solicitud completa", ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    LOG.log(Level.SEVERE, "Error cerrando conexi\u00f3n: {0}", e.getMessage());
                }
            }
        }
    }
}
