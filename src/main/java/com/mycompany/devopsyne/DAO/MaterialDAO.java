
package com.mycompany.devopsyne.DAO;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.interfaces.MaterialInterface;
import com.mycompany.devopsyne.model.Material;
import com.mycompany.devopsyne.model.TipoMaterial;
import com.mycompany.devopsyne.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Autor: Diego Alejandro Vergara Ruiz

public class MaterialDAO implements MaterialInterface {

    @Override
    public void insert(Material material) throws DAOException {
        String sql = "INSERT INTO material (tipo_material_id, peso, ancho, largo, alto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, material.getTipo().getId());
            stmt.setDouble(2, material.getPeso());
            stmt.setDouble(3, material.getAncho());
            stmt.setDouble(4, material.getLargo());
            stmt.setDouble(5, material.getAlto());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    material.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar Material", e);
        }
    }

    @Override
    public Optional<Material> findById(long id) throws DAOException {
        String sql = "SELECT id, tipo_material_id, peso, ancho, largo, alto FROM material WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar Material por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Material> findAll() throws DAOException {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT id, tipo_material_id, peso, ancho, largo, alto FROM material";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar Material", e);
        }
        return lista;
    }

    @Override
    public List<Material> findByTipo(long tipoMaterialId) throws DAOException {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT id, tipo_material_id, peso, ancho, largo, alto FROM material WHERE tipo_material_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, tipoMaterialId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar Material por tipo", e);
        }
        return lista;
    }

    @Override
    public void update(Material material) throws DAOException {
        String sql = "UPDATE material SET tipo_material_id = ?, peso = ?, ancho = ?, largo = ?, alto = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, material.getTipo().getId());
            stmt.setDouble(2, material.getPeso());
            stmt.setDouble(3, material.getAncho());
            stmt.setDouble(4, material.getLargo());
            stmt.setDouble(5, material.getAlto());
            stmt.setLong(6, material.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar Material", e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        String sql = "DELETE FROM material WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar Material", e);
        }
    }

    // Método privado para mapear ResultSet a Material
    private Material map(ResultSet rs) throws SQLException {
        Material material = new Material();
        material.setId(rs.getLong("id"));
        
        // Carga solo el TipoMaterial con ID 
        TipoMaterial tipo = new TipoMaterial();
        tipo.setId(rs.getLong("tipo_material_id"));
        material.setTipo(tipo);

        material.setPeso(rs.getDouble("peso"));
        material.setAncho(rs.getDouble("ancho"));
        material.setLargo(rs.getDouble("largo"));
        material.setAlto(rs.getDouble("alto"));
        return material;
    }
}
