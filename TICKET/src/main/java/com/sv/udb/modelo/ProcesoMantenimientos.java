/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "proceso_mantenimientos", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoMantenimientos.findAll", query = "SELECT p FROM ProcesoMantenimientos p"),
    @NamedQuery(name = "ProcesoMantenimientos.findByCodiProcMant", query = "SELECT p FROM ProcesoMantenimientos p WHERE p.codiProcMant = :codiProcMant"),
    @NamedQuery(name = "ProcesoMantenimientos.findByFechProcMant", query = "SELECT p FROM ProcesoMantenimientos p WHERE p.fechProcMant = :fechProcMant"),
    @NamedQuery(name = "ProcesoMantenimientos.findByCodiUsua", query = "SELECT p FROM ProcesoMantenimientos p WHERE p.codiUsua = :codiUsua"),
    @NamedQuery(name = "ProcesoMantenimientos.findByEstaProcMant", query = "SELECT p FROM ProcesoMantenimientos p WHERE p.estaProcMant = :estaProcMant")})
public class ProcesoMantenimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_proc_mant")
    private Integer codiProcMant;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc_proc_mant")
    private String descProcMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_proc_mant")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechProcMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_usua")
    private int codiUsua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_proc_mant")
    private boolean estaProcMant;
    @JoinColumn(name = "codi_corr_mant", referencedColumnName = "codi_corr_mant")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CorrelativoMantenimientos codiCorrMant;

    public ProcesoMantenimientos() {
    }

    public ProcesoMantenimientos(Integer codiProcMant) {
        this.codiProcMant = codiProcMant;
    }

    public ProcesoMantenimientos(Integer codiProcMant, String descProcMant, Date fechProcMant, int codiUsua, boolean estaProcMant) {
        this.codiProcMant = codiProcMant;
        this.descProcMant = descProcMant;
        this.fechProcMant = fechProcMant;
        this.codiUsua = codiUsua;
        this.estaProcMant = estaProcMant;
    }

    public Integer getCodiProcMant() {
        return codiProcMant;
    }

    public void setCodiProcMant(Integer codiProcMant) {
        this.codiProcMant = codiProcMant;
    }

    public String getDescProcMant() {
        return descProcMant;
    }

    public void setDescProcMant(String descProcMant) {
        this.descProcMant = descProcMant;
    }

    public Date getFechProcMant() {
        return fechProcMant;
    }

    public void setFechProcMant(Date fechProcMant) {
        this.fechProcMant = fechProcMant;
    }

    public int getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(int codiUsua) {
        this.codiUsua = codiUsua;
    }

    public boolean getEstaProcMant() {
        return estaProcMant;
    }

    public void setEstaProcMant(boolean estaProcMant) {
        this.estaProcMant = estaProcMant;
    }

    public CorrelativoMantenimientos getCodiCorrMant() {
        return codiCorrMant;
    }

    public void setCodiCorrMant(CorrelativoMantenimientos codiCorrMant) {
        this.codiCorrMant = codiCorrMant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiProcMant != null ? codiProcMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoMantenimientos)) {
            return false;
        }
        ProcesoMantenimientos other = (ProcesoMantenimientos) object;
        if ((this.codiProcMant == null && other.codiProcMant != null) || (this.codiProcMant != null && !this.codiProcMant.equals(other.codiProcMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.ProcesoMantenimientos[ codiProcMant=" + codiProcMant + " ]";
    }
    
}
