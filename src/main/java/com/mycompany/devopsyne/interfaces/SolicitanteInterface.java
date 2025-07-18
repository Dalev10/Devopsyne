
package com.mycompany.devopsyne.interfaces;

import com.mycompany.devopsyne.model.Solicitante;
import java.util.List;
import java.util.Optional;

// Autor: Diego Alejandro Vergara Ruiz

public interface SolicitanteInterface {
    void insert(Solicitante solicitante) throws DAOException;
    Optional<Solicitante> findById(long id) throws DAOException;
    Optional<Solicitante> findByEmail(String email) throws DAOException;
    List<Solicitante> findAll() throws DAOException;
    void update(Solicitante solicitante) throws DAOException;
    void delete(long id) throws DAOException;
}
