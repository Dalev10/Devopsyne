
package com.mycompany.devopsyne.interfaces;

import com.mycompany.devopsyne.model.TipoMaterial;
import java.util.List;
import java.util.Optional;

// Autor: Diego Alejandro Vergara Ruiz

public interface TipoMaterialInterface {
    void insert(TipoMaterial tipo) throws DAOException;
    Optional<TipoMaterial> findById(long id) throws DAOException;
    Optional<TipoMaterial> findByNombre(String nombre) throws DAOException;
    List<TipoMaterial> findAll() throws DAOException;
    void update(TipoMaterial tipo) throws DAOException;
    void delete(long id) throws DAOException;
}
