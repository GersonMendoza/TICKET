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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "ubicaciones_mantenimiento", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionesMantenimiento.findAll", query = "SELECT u FROM UbicacionesMantenimiento u"),
    @NamedQuery(name = "UbicacionesMantenimiento.findByCodiUbicMant", query = "SELECT u FROM UbicacionesMantenimiento u WHERE u.codiUbicMant = :codiUbicMant")})
public class UbicacionesMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_ubic_mant")
    private Integer codiUbicMant;
    @JoinColumn(name = "codi_ubic", referencedColumnName = "codi_ubic")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ubicaciones codiUbic;
    @JoinColumn(name = "codi_mant", referencedColumnName = "codi_mant")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mantenimientos codiMant;

    public UbicacionesMantenimiento() {
    }

    public UbicacionesMantenimiento(Integer codiUbicMant) {
        this.codiUbicMant = codiUbicMant;
    }

    public Integer getCodiUbicMant() {
        return codiUbicMant;
    }

    public void setCodiUbicMant(Integer codiUbicMant) {
        this.codiUbicMant = codiUbicMant;
    }

    public Ubicaciones getCodiUbic() {
        return codiUbic;
    }

    public void setCodiUbic(Ubicaciones codiUbic) {
        this.codiUbic = codiUbic;
    }

    public Mantenimientos getCodiMant() {
        return codiMant;
    }

    public void setCodiMant(Mantenimientos codiMant) {
        this.codiMant = codiMant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUbicMant != null ? codiUbicMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionesMantenimiento)) {
            return false;
        }
        UbicacionesMantenimiento other = (UbicacionesMantenimiento) object;
        if ((this.codiUbicMant == null && other.codiUbicMant != null) || (this.codiUbicMant != null && !this.codiUbicMant.equals(other.codiUbicMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.UbicacionesMantenimiento[ codiUbicMant=" + codiUbicMant + " ]";
    }
    
}
