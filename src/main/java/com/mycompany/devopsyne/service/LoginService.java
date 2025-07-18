
package com.mycompany.devopsyne.service;

import com.mycompany.devopsyne.interfaces.DAOException;
import com.mycompany.devopsyne.DAO.SolicitanteDAO;
import com.mycompany.devopsyne.model.Solicitante;
import com.mycompany.devopsyne.util.HashUtil;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

// Autor: Diego Alejandro Vergara Ruiz

public class LoginService {

    private static final Logger LOG = Logger.getLogger(LoginService.class.getName());

    private final SolicitanteDAO solicitanteDAO;

    public LoginService(SolicitanteDAO solicitanteDAO) {
        this.solicitanteDAO = solicitanteDAO;
    }

    public boolean autenticar(String email, String plainPassword) {
        if (email == null || plainPassword == null || email.isBlank() || plainPassword.isBlank()) {
            return false;
        }
        try {
            Optional<Solicitante> opt = solicitanteDAO.findByEmail(email.trim().toLowerCase());
            if (opt.isEmpty()) return false;
            
            // Usa comparación segura
            return HashUtil.matches(plainPassword, opt.get().getPassHash());
        } catch (DAOException ex) {
            LOG.log(Level.SEVERE, "Error al autenticar usuario: {0}", ex.getMessage());
            return false; // o throw new LoginException("Problema de autenticación", ex);
        }
    }
    
    public Optional<Solicitante> autenticarYObtener(String email, String plainPassword) throws DAOException {
        if (email == null || plainPassword == null || email.isBlank() || plainPassword.isBlank()) {
            return Optional.empty();
        }
        Optional<Solicitante> opt = solicitanteDAO.findByEmail(email.trim().toLowerCase());
        if (opt.isPresent() && HashUtil.matches(plainPassword, opt.get().getPassHash())) {
            return opt;
        }
        return Optional.empty();
    }
}
