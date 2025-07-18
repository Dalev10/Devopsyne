
package com.mycompany.devopsyne.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Autor: Diego Alejandro Vergara Ruiz

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/deposyne?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "jules_eliot_980";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("No se pudo cargar el driver de MySQL", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}