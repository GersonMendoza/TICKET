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
@Table(name = "proceso_solicitudes", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoSolicitudes.findAll", query = "SELECT p FROM ProcesoSolicitudes p"),
    @NamedQuery(name = "ProcesoSolicitudes.findByCodiProcSoli", query = "SELECT p FROM ProcesoSolicitudes p WHERE p.codiProcSoli = :codiProcSoli"),
    @NamedQuery(name = "ProcesoSolicitudes.findByFechProcSoli", query = "SELECT p FROM ProcesoSolicitudes p WHERE p.fechProcSoli = :fechProcSoli"),
    @NamedQuery(name = "ProcesoSolicitudes.findByEstaProcSoli", query = "SELECT p FROM ProcesoSolicitudes p WHERE p.estaProcSoli = :estaProcSoli")})
public class ProcesoSolicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_proc_soli")
    private Integer codiProcSoli;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc_proc_soli")
    private String descProcSoli;
    @Column(name = "fech_proc_soli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechProcSoli;
    @Column(name = "esta_proc_soli")
    private Boolean estaProcSoli;
    @JoinColumn(name = "codi_soli", referencedColumnName = "codi_soli")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitudes codiSoli;

    public ProcesoSolicitudes() {
    }

    public ProcesoSolicitudes(Integer codiProcSoli) {
        this.codiProcSoli = codiProcSoli;
    }

    public ProcesoSolicitudes(Integer codiProcSoli, String descProcSoli) {
        this.codiProcSoli = codiProcSoli;
        this.descProcSoli = descProcSoli;
    }

    public Integer getCodiProcSoli() {
        return codiProcSoli;
    }

    public void setCodiProcSoli(Integer codiProcSoli) {
        this.codiProcSoli = codiProcSoli;
    }

    public String getDescProcSoli() {
        return descProcSoli;
    }

    public void setDescProcSoli(String descProcSoli) {
        this.descProcSoli = descProcSoli;
    }

    public Date getFechProcSoli() {
        return fechProcSoli;
    }

    public void setFechProcSoli(Date fechProcSoli) {
        this.fechProcSoli = fechProcSoli;
    }

    public Boolean getEstaProcSoli() {
        return estaProcSoli;
    }

    public void setEstaProcSoli(Boolean estaProcSoli) {
        this.estaProcSoli = estaProcSoli;
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
        hash += (codiProcSoli != null ? codiProcSoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoSolicitudes)) {
            return false;
        }
        ProcesoSolicitudes other = (ProcesoSolicitudes) object;
        if ((this.codiProcSoli == null && other.codiProcSoli != null) || (this.codiProcSoli != null && !this.codiProcSoli.equals(other.codiProcSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.ProcesoSolicitudes[ codiProcSoli=" + codiProcSoli + " ]";
    }
    
}
