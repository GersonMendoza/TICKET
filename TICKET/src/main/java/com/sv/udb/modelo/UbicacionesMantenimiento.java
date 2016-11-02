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
    @NamedQuery(name = "UbicacionesMantenimiento.findByCodiUbiMant", query = "SELECT u FROM UbicacionesMantenimiento u WHERE u.codiUbiMant = :codiUbiMant")})
public class UbicacionesMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_ubi_mant")
    private Integer codiUbiMant;
    @JoinColumn(name = "codi_ubic", referencedColumnName = "codi_ubic")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ubicaciones codiUbic;
    @JoinColumn(name = "codi_mant", referencedColumnName = "codi_mant")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mantenimientos codiMant;

    public UbicacionesMantenimiento() {
    }

    public UbicacionesMantenimiento(Integer codiUbiMant) {
        this.codiUbiMant = codiUbiMant;
    }

    public Integer getCodiUbiMant() {
        return codiUbiMant;
    }

    public void setCodiUbiMant(Integer codiUbiMant) {
        this.codiUbiMant = codiUbiMant;
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
        hash += (codiUbiMant != null ? codiUbiMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionesMantenimiento)) {
            return false;
        }
        UbicacionesMantenimiento other = (UbicacionesMantenimiento) object;
        if ((this.codiUbiMant == null && other.codiUbiMant != null) || (this.codiUbiMant != null && !this.codiUbiMant.equals(other.codiUbiMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.UbicacionesMantenimiento[ codiUbiMant=" + codiUbiMant + " ]";
    }
    
}
