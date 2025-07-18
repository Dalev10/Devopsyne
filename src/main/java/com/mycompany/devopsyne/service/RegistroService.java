
package com.mycompany.devopsyne.service;

import com.mycompany.devopsyne.DAO.SolicitanteDAO;
import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.util.HashUtil;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

// Autor: Diego Alejandro Vergara Ruiz

public class RegistroService {

    private static final Logger LOG = Logger.getLogger(RegistroService.class.getName());
    private final SolicitanteDAO solicitanteDAO;

    public RegistroService(SolicitanteDAO solicitanteDAO) {
        this.solicitanteDAO = solicitanteDAO;
    }

    /**
     * Registra un nuevo solicitante en el sistema, validando previamente
     * que el email y la identificación no estén registrados.
     *
     * @param solicitante objeto con datos del formulario
     * @param plainPassword contraseña en texto plano
     * @return el solicitante insertado con ID asignado
     * @throws RegistroException si hay alguna validación fallida o problema en BD
     */
    
    public Solicitante registrarNuevoSolicitante(Solicitante solicitante, String plainPassword) throws RegistroException {
        try {
            // Validación básica
            if (solicitante.getNombre() == null || solicitante.getNombre().isBlank())
                throw new RegistroException("El nombre es obligatorio");
            if (solicitante.getEmail() == null || solicitante.getEmail().isBlank())
                throw new RegistroException("El email es obligatorio");
            if (plainPassword == null || plainPassword.isBlank())
                throw new RegistroException("La contraseña es obligatoria");
            if (solicitante.getIdentificacion() == null || solicitante.getIdentificacion().isBlank())
                throw new RegistroException("La identificación es obligatoria");

            // Validar duplicidad por email
            Optional<Solicitante> existente = solicitanteDAO.findByEmail(solicitante.getEmail().trim().toLowerCase());
            if (existente.isPresent())
                throw new RegistroException("Ya existe un usuario con ese email.");


            // Hashear contraseña
            String hash = HashUtil.hash(plainPassword);
            solicitante.setPassHash(hash);

            // Insertar en BD
            solicitanteDAO.insert(solicitante);

            return solicitante;
        } catch (DAOException ex) {
            LOG.log(Level.SEVERE, "Error en registro: {0}", ex.getMessage());
            throw new RegistroException("Ocurrió un error al registrar el usuario.", ex);
        }
    }
}
