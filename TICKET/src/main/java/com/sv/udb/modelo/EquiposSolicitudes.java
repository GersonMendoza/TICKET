/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "equipos_solicitudes", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquiposSolicitudes.findAll", query = "SELECT e FROM EquiposSolicitudes e"),
    @NamedQuery(name = "EquiposSolicitudes.findByCodiEquiSoli", query = "SELECT e FROM EquiposSolicitudes e WHERE e.codiEquiSoli = :codiEquiSoli")})
public class EquiposSolicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_equi_soli")
    private Integer codiEquiSoli;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc_equi_soli")
    private String descEquiSoli;
    @JoinColumn(name = "codi_soli", referencedColumnName = "codi_soli")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitudes codiSoli;
    @JoinColumn(name = "codi_equi", referencedColumnName = "codi_equi")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipos codiEqui;

    public EquiposSolicitudes() {
    }

    public EquiposSolicitudes(Integer codiEquiSoli) {
        this.codiEquiSoli = codiEquiSoli;
    }

    public EquiposSolicitudes(Integer codiEquiSoli, String descEquiSoli) {
        this.codiEquiSoli = codiEquiSoli;
        this.descEquiSoli = descEquiSoli;
    }

    public Integer getCodiEquiSoli() {
        return codiEquiSoli;
    }

    public void setCodiEquiSoli(Integer codiEquiSoli) {
        this.codiEquiSoli = codiEquiSoli;
    }

    public String getDescEquiSoli() {
        return descEquiSoli;
    }

    public void setDescEquiSoli(String descEquiSoli) {
        this.descEquiSoli = descEquiSoli;
    }

    public Solicitudes getCodiSoli() {
        return codiSoli;
    }

    public void setCodiSoli(Solicitudes codiSoli) {
        this.codiSoli = codiSoli;
    }

    public Equipos getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(Equipos codiEqui) {
        this.codiEqui = codiEqui;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEquiSoli != null ? codiEquiSoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquiposSolicitudes)) {
            return false;
        }
        EquiposSolicitudes other = (EquiposSolicitudes) object;
        if ((this.codiEquiSoli == null && other.codiEquiSoli != null) || (this.codiEquiSoli != null && !this.codiEquiSoli.equals(other.codiEquiSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.EquiposSolicitudes[ codiEquiSoli=" + codiEquiSoli + " ]";
    }
    
}
