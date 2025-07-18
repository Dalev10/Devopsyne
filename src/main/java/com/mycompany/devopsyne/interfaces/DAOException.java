
package com.mycompany.devopsyne.interfaces;

// Autor: Diego Alejandro Vergara Ruiz

public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }
    public DAOException(Throwable cause) {
        super(cause);
    }
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}