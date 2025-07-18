
package com.mycompany.devopsyne.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Autor: Diego Alejandro Vergara Ruiz

@Embeddable
public class SolicitudMaterialId implements Serializable {
    private Long solicitudId;
    private Long materialId;

    public SolicitudMaterialId() {
    }
    
    public SolicitudMaterialId(Long solicitudId, Long materialId) {
        this.solicitudId = solicitudId;
        this.materialId = materialId;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolicitudMaterialId)) return false;
        SolicitudMaterialId that = (SolicitudMaterialId) o;
        return Objects.equals(solicitudId, that.solicitudId) &&
               Objects.equals(materialId, that.materialId);
    }
    @Override public int hashCode() { return Objects.hash(solicitudId, materialId); }
}
