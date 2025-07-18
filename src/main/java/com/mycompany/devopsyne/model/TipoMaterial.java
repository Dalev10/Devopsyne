
package com.mycompany.devopsyne.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// Autor: Diego Alejandro Vergara Ruiz

@Entity
@Table(name = "tipo_material")
public class TipoMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40, unique = true)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Material> materiales;

    public TipoMaterial() {
    }

    public TipoMaterial(Long id, String nombre, String descripcion, List<Material> materiales) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.materiales = materiales;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    @Override
    public String toString() {
        return "TipoMaterial{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", materiales=" + materiales + '}';
    }
    
    
}
