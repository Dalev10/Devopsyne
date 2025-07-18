
package com.mycompany.devopsyne.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


// Autor: Diego Alejandro Vergara Ruiz

@Entity
@Table(name = "material")
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_material_id", nullable = false)
    private TipoMaterial tipo;

    @Column(precision = 8, scale = 2, nullable = false)
    private double peso;
    private double ancho;
    private double largo;
    private double alto;

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudMaterial> detalles;

    public Material() {
    }

    public Material(Long id, TipoMaterial tipo, double peso, double ancho, double largo, double alto, List<SolicitudMaterial> detalles) {
        this.id = id;
        this.tipo = tipo;
        this.peso = peso;
        this.ancho = ancho;
        this.largo = largo;
        this.alto = alto;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public List<SolicitudMaterial> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<SolicitudMaterial> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", tipo=" + tipo + ", peso=" + peso + ", ancho=" + ancho + ", largo=" + largo + ", alto=" + alto + ", detalles=" + detalles + '}';
    }
    
    
}
