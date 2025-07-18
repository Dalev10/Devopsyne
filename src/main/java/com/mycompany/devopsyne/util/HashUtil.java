
package com.mycompany.devopsyne.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Autor: Diego Alejandro Vergara Ruiz

public class HashUtil {

    /**
     * Genera hash SHA-256 en formato hexadecimal (64 caracteres).
     *
     * @param plainText el texto plano a hashear
     * @return el hash en hexadecimal
     * @throws RuntimeException si el algoritmo SHA-256 no está disponible
     */
    public static String hash(String plainText) {
        if (plainText == null) {
            throw new IllegalArgumentException("El texto no puede ser null");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No se encontró el algoritmo SHA-256", e);
        }
    }

    /**
     * Verifica si el hash del texto plano coincide con un hash dado.
     *
     * @param plainText el texto plano
     * @param expectedHash el hash esperado (hexadecimal)
     * @return true si coincide, false en caso contrario
     */
    public static boolean matches(String plainText, String expectedHash) {
        if (plainText == null || expectedHash == null) return false;
        String computedHash = hash(plainText);
        return computedHash.equalsIgnoreCase(expectedHash);
    }

    /**
     * Convierte bytes a representación hexadecimal.
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
