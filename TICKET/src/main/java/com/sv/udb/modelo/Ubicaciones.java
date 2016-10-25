/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "ubicaciones", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubicaciones.findAll", query = "SELECT u FROM Ubicaciones u"),
    @NamedQuery(name = "Ubicaciones.findByCodiUbi", query = "SELECT u FROM Ubicaciones u WHERE u.codiUbi = :codiUbi"),
    @NamedQuery(name = "Ubicaciones.findByNombUbi", query = "SELECT u FROM Ubicaciones u WHERE u.nombUbi = :nombUbi")})
public class Ubicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_ubi")
    private Integer codiUbi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_ubi")
    private String nombUbi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiUbi", fetch = FetchType.LAZY)
    private List<UbicacionesMantenimiento> ubicacionesMantenimientoList;
    @OneToMany(mappedBy = "codiUbi", fetch = FetchType.LAZY)
    private List<Solicitudes> solicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiUbi", fetch = FetchType.LAZY)
    private List<Equipos> equiposList;

    public Ubicaciones() {
    }

    public Ubicaciones(Integer codiUbi) {
        this.codiUbi = codiUbi;
    }

    public Ubicaciones(Integer codiUbi, String nombUbi) {
        this.codiUbi = codiUbi;
        this.nombUbi = nombUbi;
    }

    public Integer getCodiUbi() {
        return codiUbi;
    }

    public void setCodiUbi(Integer codiUbi) {
        this.codiUbi = codiUbi;
    }

    public String getNombUbi() {
        return nombUbi;
    }

    public void setNombUbi(String nombUbi) {
        this.nombUbi = nombUbi;
    }

    @XmlTransient
    public List<UbicacionesMantenimiento> getUbicacionesMantenimientoList() {
        return ubicacionesMantenimientoList;
    }

    public void setUbicacionesMantenimientoList(List<UbicacionesMantenimiento> ubicacionesMantenimientoList) {
        this.ubicacionesMantenimientoList = ubicacionesMantenimientoList;
    }

    @XmlTransient
    public List<Solicitudes> getSolicitudesList() {
        return solicitudesList;
    }

    public void setSolicitudesList(List<Solicitudes> solicitudesList) {
        this.solicitudesList = solicitudesList;
    }

    @XmlTransient
    public List<Equipos> getEquiposList() {
        return equiposList;
    }

    public void setEquiposList(List<Equipos> equiposList) {
        this.equiposList = equiposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUbi != null ? codiUbi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicaciones)) {
            return false;
        }
        Ubicaciones other = (Ubicaciones) object;
        if ((this.codiUbi == null && other.codiUbi != null) || (this.codiUbi != null && !this.codiUbi.equals(other.codiUbi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Ubicaciones[ codiUbi=" + codiUbi + " ]";
    }
    
}
