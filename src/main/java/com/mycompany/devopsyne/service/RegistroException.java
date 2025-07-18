
package com.mycompany.devopsyne.service;

//Autor: Diego Alejandro Vergara Ruiz

public class RegistroException extends Exception {
    public RegistroException(String message) {
        super(message);
    }

    public RegistroException(String message, Throwable cause) {
        super(message, cause);
    }
}

