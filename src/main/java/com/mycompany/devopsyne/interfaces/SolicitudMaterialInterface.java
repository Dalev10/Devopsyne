
package com.mycompany.devopsyne.interfaces;

import com.mycompany.devopsyne.model.SolicitudMaterial;
import com.mycompany.devopsyne.model.SolicitudMaterialId;
import java.util.List;

// Autor: Diego Alejandro Vergara Ruiz

public interface SolicitudMaterialInterface {
    void insert(SolicitudMaterial detalle) throws DAOException;
    List<SolicitudMaterial> findBySolicitud(long solicitudId) throws DAOException;
    void deleteBySolicitud(long solicitudId) throws DAOException;
    void delete(SolicitudMaterialId id) throws DAOException;
}
