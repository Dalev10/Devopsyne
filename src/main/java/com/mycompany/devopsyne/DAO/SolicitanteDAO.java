
package com.mycompany.devopsyne.DAO;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.interfaces.SolicitanteInterface;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.util.DBConnection;
import java.sql.*;
import java.util.*;

//Autor: Diego Alejandro Vergara Ruiz

public class SolicitanteDAO implements SolicitanteInterface {

    @Override
    public void insert(Solicitante solicitante) throws DAOException {
        String sql = "INSERT INTO solicitante (nombre, identificacion, email, telefono, direccion, pass_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, solicitante.getNombre());
            stmt.setString(2, solicitante.getIdentificacion());
            stmt.setString(3, solicitante.getEmail());
            stmt.setString(4, solicitante.getTelefono());
            stmt.setString(5, solicitante.getDireccion());
            stmt.setString(6, solicitante.getPassHash());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    solicitante.setId(rs.getLong(1));
                }
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al insertar Solicitante", ex);
        }
    }

    @Override
    public Optional<Solicitante> findById(long id) throws DAOException {
        String sql = "SELECT * FROM solicitante WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DAOException("Error al buscar Solicitante por ID", ex);
        }
    }

    @Override
    public Optional<Solicitante> findByEmail(String email) throws DAOException {
        String sql = "SELECT * FROM solicitante WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DAOException("Error al buscar Solicitante por email", ex);
        }
    }

    @Override
    public List<Solicitante> findAll() throws DAOException {
        List<Solicitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitante";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al listar solicitantes", ex);
        }
        return lista;
    }

    @Override
    public void update(Solicitante solicitante) throws DAOException {
        String sql = "UPDATE solicitante SET nombre = ?, identificacion = ?, email = ?, telefono = ?, direccion = ?, pass_hash = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, solicitante.getNombre());
            stmt.setString(2, solicitante.getIdentificacion());
            stmt.setString(3, solicitante.getEmail());
            stmt.setString(4, solicitante.getTelefono());
            stmt.setString(5, solicitante.getDireccion());
            stmt.setString(6, solicitante.getPassHash());
            stmt.setLong(7, solicitante.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Error al actualizar Solicitante", ex);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        String sql = "DELETE FROM solicitante WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar Solicitante", ex);
        }
    }

    private Solicitante map(ResultSet rs) throws SQLException {
        Solicitante s = new Solicitante();
        s.setId(rs.getLong("id"));
        s.setNombre(rs.getString("nombre"));
        s.setIdentificacion(rs.getString("identificacion"));
        s.setEmail(rs.getString("email"));
        s.setTelefono(rs.getString("telefono"));
        s.setDireccion(rs.getString("direccion"));
        s.setPassHash(rs.getString("pass_hash"));
        return s;
    }
}
