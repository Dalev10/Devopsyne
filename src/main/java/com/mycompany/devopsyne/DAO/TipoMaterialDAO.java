
package com.mycompany.devopsyne.DAO;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.interfaces.TipoMaterialInterface;
import com.mycompany.devopsyne.model.TipoMaterial;
import com.mycompany.devopsyne.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Autor: Diego Alejandro Vergara Ruiz

public class TipoMaterialDAO implements TipoMaterialInterface {

    @Override
    public void insert(TipoMaterial tipo) throws DAOException {
        String sql = "INSERT INTO tipo_material (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipo.getNombre());
            stmt.setString(2, tipo.getDescripcion());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tipo.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar TipoMaterial", e);
        }
    }

    @Override
    public Optional<TipoMaterial> findById(long id) throws DAOException {
        String sql = "SELECT id, nombre, descripcion FROM tipo_material WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar TipoMaterial por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<TipoMaterial> findByNombre(String nombre) throws DAOException {
        String sql = "SELECT id, nombre, descripcion FROM tipo_material WHERE nombre = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar TipoMaterial por nombre", e);
        }
        return Optional.empty();
    }

    @Override
    public List<TipoMaterial> findAll() throws DAOException {
        List<TipoMaterial> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM tipo_material";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar TipoMaterial", e);
        }
        return lista;
    }

    @Override
    public void update(TipoMaterial tipo) throws DAOException {
        String sql = "UPDATE tipo_material SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo.getNombre());
            stmt.setString(2, tipo.getDescripcion());
            stmt.setLong(3, tipo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar TipoMaterial", e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        String sql = "DELETE FROM tipo_material WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar TipoMaterial", e);
        }
    }

    // Método privado para mapear ResultSet a TipoMaterial
    private TipoMaterial map(ResultSet rs) throws SQLException {
        TipoMaterial tipo = new TipoMaterial();
        tipo.setId(rs.getLong("id"));
        tipo.setNombre(rs.getString("nombre"));
        tipo.setDescripcion(rs.getString("descripcion"));
        return tipo;
    }
}
