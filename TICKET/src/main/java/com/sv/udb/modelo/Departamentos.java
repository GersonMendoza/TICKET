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
@Table(name = "departamentos", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByCodiDepa", query = "SELECT d FROM Departamentos d WHERE d.codiDepa = :codiDepa"),
    @NamedQuery(name = "Departamentos.findByNombDepa", query = "SELECT d FROM Departamentos d WHERE d.nombDepa = :nombDepa"),
    @NamedQuery(name = "Departamentos.findByFechIngrDepa", query = "SELECT d FROM Departamentos d WHERE d.fechIngrDepa = :fechIngrDepa"),
    @NamedQuery(name = "Departamentos.findByFechBajaDepa", query = "SELECT d FROM Departamentos d WHERE d.fechBajaDepa = :fechBajaDepa"),
    @NamedQuery(name = "Departamentos.findByEstaDepa", query = "SELECT d FROM Departamentos d WHERE d.estaDepa = :estaDepa")})
public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_depa")
    private Integer codiDepa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_depa")
    private String nombDepa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_ingr_depa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechIngrDepa;
    @Column(name = "fech_baja_depa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechBajaDepa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_depa")
    private boolean estaDepa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiDepa", fetch = FetchType.LAZY)
    private List<Mantenimientos> mantenimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiDepa", fetch = FetchType.LAZY)
    private List<Solicitudes> solicitudesList;

    public Departamentos() {
    }

    public Departamentos(Integer codiDepa) {
        this.codiDepa = codiDepa;
    }

    public Departamentos(Integer codiDepa, String nombDepa, Date fechIngrDepa, boolean estaDepa) {
        this.codiDepa = codiDepa;
        this.nombDepa = nombDepa;
        this.fechIngrDepa = fechIngrDepa;
        this.estaDepa = estaDepa;
    }

    public Integer getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Integer codiDepa) {
        this.codiDepa = codiDepa;
    }

    public String getNombDepa() {
        return nombDepa;
    }

    public void setNombDepa(String nombDepa) {
        this.nombDepa = nombDepa;
    }

    public Date getFechIngrDepa() {
        return fechIngrDepa;
    }

    public void setFechIngrDepa(Date fechIngrDepa) {
        this.fechIngrDepa = fechIngrDepa;
    }

    public Date getFechBajaDepa() {
        return fechBajaDepa;
    }

    public void setFechBajaDepa(Date fechBajaDepa) {
        this.fechBajaDepa = fechBajaDepa;
    }

    public boolean getEstaDepa() {
        return estaDepa;
    }

    public void setEstaDepa(boolean estaDepa) {
        this.estaDepa = estaDepa;
    }

    @XmlTransient
    public List<Mantenimientos> getMantenimientosList() {
        return mantenimientosList;
    }

    public void setMantenimientosList(List<Mantenimientos> mantenimientosList) {
        this.mantenimientosList = mantenimientosList;
    }

    @XmlTransient
    public List<Solicitudes> getSolicitudesList() {
        return solicitudesList;
    }

    public void setSolicitudesList(List<Solicitudes> solicitudesList) {
        this.solicitudesList = solicitudesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDepa != null ? codiDepa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.codiDepa == null && other.codiDepa != null) || (this.codiDepa != null && !this.codiDepa.equals(other.codiDepa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Departamentos[ codiDepa=" + codiDepa + " ]";
    }
    
}
