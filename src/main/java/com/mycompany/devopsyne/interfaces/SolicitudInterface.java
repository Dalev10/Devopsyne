
package com.mycompany.devopsyne.interfaces;

import com.mycompany.devopsyne.model.Solicitud;
import java.util.List;
import java.util.Optional;

// Autor: Diego Alejandro Vergara Ruiz

public interface SolicitudInterface {
    void insert(Solicitud solicitud) throws DAOException;
    Optional<Solicitud> findById(long id) throws DAOException;
    List<Solicitud> findBySolicitante(long solicitanteId) throws DAOException;
    List<Solicitud> findAll() throws DAOException;
    void update(Solicitud solicitud) throws DAOException;
    void delete(long id) throws DAOException;
}
