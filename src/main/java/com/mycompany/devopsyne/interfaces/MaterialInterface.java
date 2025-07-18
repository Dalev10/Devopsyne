
package com.mycompany.devopsyne.interfaces;

import com.mycompany.devopsyne.model.Material;
import java.util.List;
import java.util.Optional;

// Autor: Diego Alejandro Vergara Ruiz

public interface MaterialInterface {
    void insert(Material material) throws DAOException;
    Optional<Material> findById(long id) throws DAOException;
    List<Material> findAll() throws DAOException;
    List<Material> findByTipo(long tipoMaterialId) throws DAOException;
    void update(Material material) throws DAOException;
    void delete(long id) throws DAOException;   
}
