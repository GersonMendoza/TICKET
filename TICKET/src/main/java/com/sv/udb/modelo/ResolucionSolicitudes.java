/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "resolucion_solicitudes", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResolucionSolicitudes.findAll", query = "SELECT r FROM ResolucionSolicitudes r"),
    @NamedQuery(name = "ResolucionSolicitudes.findByCodiResoSoli", query = "SELECT r FROM ResolucionSolicitudes r WHERE r.codiResoSoli = :codiResoSoli"),
    @NamedQuery(name = "ResolucionSolicitudes.findByFechResoSoli", query = "SELECT r FROM ResolucionSolicitudes r WHERE r.fechResoSoli = :fechResoSoli"),
    @NamedQuery(name = "ResolucionSolicitudes.findByTipoTrabSoli", query = "SELECT r FROM ResolucionSolicitudes r WHERE r.tipoTrabSoli = :tipoTrabSoli"),
    @NamedQuery(name = "ResolucionSolicitudes.findByEstaResoSoli", query = "SELECT r FROM ResolucionSolicitudes r WHERE r.estaResoSoli = :estaResoSoli")})
public class ResolucionSolicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_reso_soli")
    private Integer codiResoSoli;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc_reso_soli")
    private String descResoSoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_reso_soli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechResoSoli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_trab_soli")
    private String tipoTrabSoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_reso_soli")
    private int estaResoSoli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiResoSoli", fetch = FetchType.LAZY)
    private List<EvaluacionResoluciones> evaluacionResolucionesList;
    @JoinColumn(name = "codi_soli", referencedColumnName = "codi_soli")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitudes codiSoli;

    public ResolucionSolicitudes() {
    }

    public ResolucionSolicitudes(Integer codiResoSoli) {
        this.codiResoSoli = codiResoSoli;
    }

    public ResolucionSolicitudes(Integer codiResoSoli, String descResoSoli, Date fechResoSoli, String tipoTrabSoli, int estaResoSoli) {
        this.codiResoSoli = codiResoSoli;
        this.descResoSoli = descResoSoli;
        this.fechResoSoli = fechResoSoli;
        this.tipoTrabSoli = tipoTrabSoli;
        this.estaResoSoli = estaResoSoli;
    }

    public Integer getCodiResoSoli() {
        return codiResoSoli;
    }

    public void setCodiResoSoli(Integer codiResoSoli) {
        this.codiResoSoli = codiResoSoli;
    }

    public String getDescResoSoli() {
        return descResoSoli;
    }

    public void setDescResoSoli(String descResoSoli) {
        this.descResoSoli = descResoSoli;
    }

    public Date getFechResoSoli() {
        return fechResoSoli;
    }

    public void setFechResoSoli(Date fechResoSoli) {
        this.fechResoSoli = fechResoSoli;
    }

    public String getTipoTrabSoli() {
        return tipoTrabSoli;
    }

    public void setTipoTrabSoli(String tipoTrabSoli) {
        this.tipoTrabSoli = tipoTrabSoli;
    }

    public int getEstaResoSoli() {
        return estaResoSoli;
    }

    public void setEstaResoSoli(int estaResoSoli) {
        this.estaResoSoli = estaResoSoli;
    }

    @XmlTransient
    public List<EvaluacionResoluciones> getEvaluacionResolucionesList() {
        return evaluacionResolucionesList;
    }

    public void setEvaluacionResolucionesList(List<EvaluacionResoluciones> evaluacionResolucionesList) {
        this.evaluacionResolucionesList = evaluacionResolucionesList;
    }

    public Solicitudes getCodiSoli() {
        return codiSoli;
    }

    public void setCodiSoli(Solicitudes codiSoli) {
        this.codiSoli = codiSoli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiResoSoli != null ? codiResoSoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucionSolicitudes)) {
            return false;
        }
        ResolucionSolicitudes other = (ResolucionSolicitudes) object;
        if ((this.codiResoSoli == null && other.codiResoSoli != null) || (this.codiResoSoli != null && !this.codiResoSoli.equals(other.codiResoSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.ResolucionSolicitudes[ codiResoSoli=" + codiResoSoli + " ]";
    }
    
}
