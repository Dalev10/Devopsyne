
package com.mycompany.devopsyne.model;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

// Autor: Diego Alejandro Vergara Ruiz

public class Solicitante implements Serializable {
    private static final long serialVersionUID = 1L;
    

    private Long id;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String identificacion;
    
    @Email
    @NotBlank
    private String email;
    
    private String telefono;
    private String direccion;
    
    private String passHash;
            
    private List<Solicitud> solicitudes;

    public Solicitante() {
    }

    public Solicitante(Long id, String nombre, String identificacion, String email, String telefono, String direccion, String passHash, List<Solicitud> solicitudes) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.passHash = passHash;
        this.solicitudes = solicitudes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return "Solicitante{" + "id=" + id + ", nombre=" + nombre + ", identificacion=" + identificacion + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + ", solicitudes=" + solicitudes + '}';
    }
    
}
