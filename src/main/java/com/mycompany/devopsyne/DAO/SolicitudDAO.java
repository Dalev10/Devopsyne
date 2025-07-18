
package com.mycompany.devopsyne.DAO;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.interfaces.SolicitudInterface;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.model.Solicitud;
import com.mycompany.devopsyne.model.EstadoSolicitud;
import com.mycompany.devopsyne.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Autor: Diego Alejandro Vergara Ruiz

public class SolicitudDAO implements SolicitudInterface {

    @Override
    public void insert(Solicitud solicitud) throws DAOException {
        try (Connection conn = com.mycompany.devopsyne.util.DBConnection.getConnection()) {
            insert(solicitud, conn);
        } catch (SQLException e) {
            throw new DAOException("Error al insertar solicitud", e);
        }
    }

    public void insert(Solicitud solicitud, Connection conn) throws DAOException {
        String sql = "INSERT INTO solicitud (solicitante_id, fecha, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, solicitud.getSolicitante().getId());
            stmt.setDate(2, new java.sql.Date(solicitud.getFecha().getTime()));
            stmt.setString(3, solicitud.getEstado().name());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    solicitud.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar solicitud en transacción", e);
        }
    }

    @Override
    public Optional<Solicitud> findById(long id) throws DAOException {
        String sql = "SELECT id, solicitante_id, fecha, estado FROM solicitud WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar solicitud por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Solicitud> findBySolicitante(long solicitanteId) throws DAOException {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT id, solicitante_id, fecha, estado FROM solicitud WHERE solicitante_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, solicitanteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar solicitudes por solicitante", e);
        }
        return lista;
    }

    @Override
    public List<Solicitud> findAll() throws DAOException {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT id, solicitante_id, fecha, estado FROM solicitud";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar todas las solicitudes", e);
        }
        return lista;
    }

    @Override
    public void update(Solicitud solicitud) throws DAOException {
        String sql = "UPDATE solicitud SET solicitante_id = ?, fecha = ?, estado = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, solicitud.getSolicitante().getId());
            stmt.setDate(2, new java.sql.Date(solicitud.getFecha().getTime()));
            stmt.setString(3, solicitud.getEstado().name());
            stmt.setLong(4, solicitud.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar solicitud", e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        String sql = "DELETE FROM solicitud WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar solicitud", e);
        }
    }

    private Solicitud map(ResultSet rs) throws SQLException {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(rs.getLong("id"));
        
        Solicitante solicitante = new Solicitante();
        solicitante.setId(rs.getLong("solicitante_id"));
        solicitud.setSolicitante(solicitante);
        
        solicitud.setFecha(rs.getDate("fecha"));
        solicitud.setEstado(EstadoSolicitud.valueOf(rs.getString("estado")));
        
        solicitud.setMateriales(new ArrayList<>()); // se inicializa vacía para cargar luego
        return solicitud;
    }
}

