
package com.mycompany.devopsyne.model;

import javax.persistence.*;
import java.io.Serializable;

// Autor: Diego Alejandro Vergara Ruiz

@Entity
@Table(name = "solicitud_material")
public class SolicitudMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SolicitudMaterialId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("solicitudId")
    @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("materialId")
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    private int cantidad;

    public SolicitudMaterial() {
    }

    public SolicitudMaterial(SolicitudMaterialId id, Solicitud solicitud, Material material, int cantidad) {
        this.id = id;
        this.solicitud = solicitud;
        this.material = material;
        this.cantidad = cantidad;
    }

    public SolicitudMaterialId getId() {
        return id;
    }

    public void setId(SolicitudMaterialId id) {
        this.id = id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "SolicitudMaterial{" + "id=" + id + ", solicitud=" + solicitud + ", material=" + material + ", cantidad=" + cantidad + '}';
    }
}