
package com.mycompany.devopsyne.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// Autor: Diego Alejandro Vergara Ruiz

@Entity
@Table(name = "solicitud")
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Solicitante solicitante;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY,
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudMaterial> materiales;

    public Solicitud() {
    }

    public Solicitud(Long id, Solicitante solicitante, Date fecha, EstadoSolicitud estado, List<SolicitudMaterial> materiales) {
        this.id = id;
        this.solicitante = solicitante;
        this.fecha = fecha;
        this.estado = estado;
        this.materiales = materiales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public List<SolicitudMaterial> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<SolicitudMaterial> materiales) {
        this.materiales = materiales;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "id=" + id + ", solicitante=" + solicitante + ", fecha=" + fecha + ", estado=" + estado + ", materiales=" + materiales + '}';
    }
    
    
}