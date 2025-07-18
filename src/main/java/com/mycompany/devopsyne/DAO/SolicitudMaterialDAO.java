
package com.mycompany.devopsyne.DAO;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.interfaces.SolicitudMaterialInterface;
import com.mycompany.devopsyne.model.SolicitudMaterial;
import com.mycompany.devopsyne.model.SolicitudMaterialId;
import com.mycompany.devopsyne.model.Solicitud;
import com.mycompany.devopsyne.model.Material;
import com.mycompany.devopsyne.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Autor: Diego Alejandro Vergara Ruiz

public class SolicitudMaterialDAO implements SolicitudMaterialInterface {

    @Override
    public void insert(SolicitudMaterial detalle) throws DAOException {
        try (Connection conn = com.mycompany.devopsyne.util.DBConnection.getConnection()) {
            insert(detalle, conn);
        } catch (SQLException e) {
            throw new DAOException("Error al insertar SolicitudMaterial", e);
        }
    }

    public void insert(SolicitudMaterial detalle, Connection conn) throws DAOException {
        String sql = "INSERT INTO solicitud_material (solicitud_id, material_id, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, detalle.getSolicitud().getId());
            stmt.setLong(2, detalle.getMaterial().getId());
            stmt.setInt(3, detalle.getCantidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al insertar detalle en transacción", e);
        }
    }

    @Override
    public List<SolicitudMaterial> findBySolicitud(long solicitudId) throws DAOException {
        List<SolicitudMaterial> lista = new ArrayList<>();
        String sql = "SELECT solicitud_id, material_id, cantidad FROM solicitud_material WHERE solicitud_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, solicitudId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar materiales por solicitud", e);
        }
        return lista;
    }

    @Override
    public void deleteBySolicitud(long solicitudId) throws DAOException {
        String sql = "DELETE FROM solicitud_material WHERE solicitud_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, solicitudId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar detalles por solicitud", e);
        }
    }

    @Override
    public void delete(SolicitudMaterialId id) throws DAOException {
        String sql = "DELETE FROM solicitud_material WHERE solicitud_id = ? AND material_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id.getSolicitudId());
            stmt.setLong(2, id.getMaterialId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar detalle específico", e);
        }
    }

    // Método privado para mapear ResultSet
    private SolicitudMaterial map(ResultSet rs) throws SQLException {
        SolicitudMaterial detalle = new SolicitudMaterial();
        
        SolicitudMaterialId id = new SolicitudMaterialId();
        id.setSolicitudId(rs.getLong("solicitud_id"));
        id.setMaterialId(rs.getLong("material_id"));
        
        detalle.setId(id);

        Solicitud solicitud = new Solicitud();
        solicitud.setId(id.getSolicitudId());
        detalle.setSolicitud(solicitud);

        Material material = new Material();
        material.setId(id.getMaterialId());
        detalle.setMaterial(material);

        detalle.setCantidad(rs.getInt("cantidad"));
        return detalle;
    }
}
