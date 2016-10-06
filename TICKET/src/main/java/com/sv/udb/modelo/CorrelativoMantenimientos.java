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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "correlativo_mantenimientos", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorrelativoMantenimientos.findAll", query = "SELECT c FROM CorrelativoMantenimientos c"),
    @NamedQuery(name = "CorrelativoMantenimientos.findByCodiCorrMant", query = "SELECT c FROM CorrelativoMantenimientos c WHERE c.codiCorrMant = :codiCorrMant"),
    @NamedQuery(name = "CorrelativoMantenimientos.findByFechCorrMant", query = "SELECT c FROM CorrelativoMantenimientos c WHERE c.fechCorrMant = :fechCorrMant"),
    @NamedQuery(name = "CorrelativoMantenimientos.findByEstaCorrMant", query = "SELECT c FROM CorrelativoMantenimientos c WHERE c.estaCorrMant = :estaCorrMant")})
public class CorrelativoMantenimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_corr_mant")
    private Integer codiCorrMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_corr_mant")
    @Temporal(TemporalType.DATE)
    private Date fechCorrMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_corr_mant")
    private boolean estaCorrMant;
    @JoinColumn(name = "codi_mant", referencedColumnName = "codi_mant")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mantenimientos codiMant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiCorrMant", fetch = FetchType.LAZY)
    private List<ProcesoMantenimientos> procesoMantenimientosList;

    public CorrelativoMantenimientos() {
    }

    public CorrelativoMantenimientos(Integer codiCorrMant) {
        this.codiCorrMant = codiCorrMant;
    }

    public CorrelativoMantenimientos(Integer codiCorrMant, Date fechCorrMant, boolean estaCorrMant) {
        this.codiCorrMant = codiCorrMant;
        this.fechCorrMant = fechCorrMant;
        this.estaCorrMant = estaCorrMant;
    }

    public Integer getCodiCorrMant() {
        return codiCorrMant;
    }

    public void setCodiCorrMant(Integer codiCorrMant) {
        this.codiCorrMant = codiCorrMant;
    }

    public Date getFechCorrMant() {
        return fechCorrMant;
    }

    public void setFechCorrMant(Date fechCorrMant) {
        this.fechCorrMant = fechCorrMant;
    }

    public boolean getEstaCorrMant() {
        return estaCorrMant;
    }

    public void setEstaCorrMant(boolean estaCorrMant) {
        this.estaCorrMant = estaCorrMant;
    }

    public Mantenimientos getCodiMant() {
        return codiMant;
    }

    public void setCodiMant(Mantenimientos codiMant) {
        this.codiMant = codiMant;
    }

    @XmlTransient
    public List<ProcesoMantenimientos> getProcesoMantenimientosList() {
        return procesoMantenimientosList;
    }

    public void setProcesoMantenimientosList(List<ProcesoMantenimientos> procesoMantenimientosList) {
        this.procesoMantenimientosList = procesoMantenimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiCorrMant != null ? codiCorrMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorrelativoMantenimientos)) {
            return false;
        }
        CorrelativoMantenimientos other = (CorrelativoMantenimientos) object;
        if ((this.codiCorrMant == null && other.codiCorrMant != null) || (this.codiCorrMant != null && !this.codiCorrMant.equals(other.codiCorrMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.CorrelativoMantenimientos[ codiCorrMant=" + codiCorrMant + " ]";
    }
    
}
