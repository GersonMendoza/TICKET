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
    @NamedQuery(name = "Ubicaciones.findByCodiUbic", query = "SELECT u FROM Ubicaciones u WHERE u.codiUbic = :codiUbic"),
    @NamedQuery(name = "Ubicaciones.findByNombUbic", query = "SELECT u FROM Ubicaciones u WHERE u.nombUbic = :nombUbic"),
    @NamedQuery(name = "Ubicaciones.findByDispEvent", query = "SELECT u FROM Ubicaciones u WHERE u.dispEvent = :dispEvent"),
    @NamedQuery(name = "Ubicaciones.findByDispCita", query = "SELECT u FROM Ubicaciones u WHERE u.dispCita = :dispCita"),
    @NamedQuery(name = "Ubicaciones.findByEstaUbic", query = "SELECT u FROM Ubicaciones u WHERE u.estaUbic = :estaUbic")})
public class Ubicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_ubic")
    private Integer codiUbic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_ubic")
    private String nombUbic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_event")
    private int dispEvent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_cita")
    private int dispCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_ubic")
    private boolean estaUbic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiUbic", fetch = FetchType.LAZY)
    private List<UbicacionesMantenimiento> ubicacionesMantenimientoList;
    @OneToMany(mappedBy = "codiUbic", fetch = FetchType.LAZY)
    private List<Solicitudes> solicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiUbic", fetch = FetchType.LAZY)
    private List<Equipos> equiposList;

    public Ubicaciones() {
    }

    public Ubicaciones(Integer codiUbic) {
        this.codiUbic = codiUbic;
    }

    public Ubicaciones(Integer codiUbic, String nombUbic, int dispEvent, int dispCita, boolean estaUbic) {
        this.codiUbic = codiUbic;
        this.nombUbic = nombUbic;
        this.dispEvent = dispEvent;
        this.dispCita = dispCita;
        this.estaUbic = estaUbic;
    }

    public Integer getCodiUbic() {
        return codiUbic;
    }

    public void setCodiUbic(Integer codiUbic) {
        this.codiUbic = codiUbic;
    }

    public String getNombUbic() {
        return nombUbic;
    }

    public void setNombUbic(String nombUbic) {
        this.nombUbic = nombUbic;
    }

    public int getDispEvent() {
        return dispEvent;
    }

    public void setDispEvent(int dispEvent) {
        this.dispEvent = dispEvent;
    }

    public int getDispCita() {
        return dispCita;
    }

    public void setDispCita(int dispCita) {
        this.dispCita = dispCita;
    }

    public boolean getEstaUbic() {
        return estaUbic;
    }

    public void setEstaUbic(boolean estaUbic) {
        this.estaUbic = estaUbic;
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
        hash += (codiUbic != null ? codiUbic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicaciones)) {
            return false;
        }
        Ubicaciones other = (Ubicaciones) object;
        if ((this.codiUbic == null && other.codiUbic != null) || (this.codiUbic != null && !this.codiUbic.equals(other.codiUbic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Ubicaciones[ codiUbic=" + codiUbic + " ]";
    }
    
}
