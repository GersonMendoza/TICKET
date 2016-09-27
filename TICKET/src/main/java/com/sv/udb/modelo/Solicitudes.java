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
 * @author Alexander
 */
@Entity
@Table(name = "solicitudes", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s"),
    @NamedQuery(name = "Solicitudes.findByCodiSoli", query = "SELECT s FROM Solicitudes s WHERE s.codiSoli = :codiSoli"),
    @NamedQuery(name = "Solicitudes.findByCodiUsua", query = "SELECT s FROM Solicitudes s WHERE s.codiUsua = :codiUsua"),
    @NamedQuery(name = "Solicitudes.findByCodiEnca", query = "SELECT s FROM Solicitudes s WHERE s.codiEnca = :codiEnca"),
    @NamedQuery(name = "Solicitudes.findByCodiEqui", query = "SELECT s FROM Solicitudes s WHERE s.codiEqui = :codiEqui"),
    @NamedQuery(name = "Solicitudes.findByCodiUbic", query = "SELECT s FROM Solicitudes s WHERE s.codiUbic = :codiUbic"),
    @NamedQuery(name = "Solicitudes.findByFechHoraSoli", query = "SELECT s FROM Solicitudes s WHERE s.fechHoraSoli = :fechHoraSoli"),
    @NamedQuery(name = "Solicitudes.findByTiemResoSoli", query = "SELECT s FROM Solicitudes s WHERE s.tiemResoSoli = :tiemResoSoli"),
    @NamedQuery(name = "Solicitudes.findByPrioSoli", query = "SELECT s FROM Solicitudes s WHERE s.prioSoli = :prioSoli"),
    @NamedQuery(name = "Solicitudes.findByEstaSoli", query = "SELECT s FROM Solicitudes s WHERE s.estaSoli = :estaSoli")})
public class Solicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_soli")
    private Integer codiSoli;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desc_soli")
    private String descSoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_usua")
    private int codiUsua;
    @Column(name = "codi_enca")
    private Integer codiEnca;
    @Column(name = "codi_equi")
    private Integer codiEqui;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_ubic")
    private int codiUbic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_hora_soli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechHoraSoli;
    @Column(name = "tiem_reso_soli")
    private Integer tiemResoSoli;
    @Size(max = 50)
    @Column(name = "prio_soli")
    private String prioSoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_soli")
    private int estaSoli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiSoli", fetch = FetchType.EAGER)
    private List<ResolucionSolicitudes> resolucionSolicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiSoli", fetch = FetchType.EAGER)
    private List<ProcesoSolicitudes> procesoSolicitudesList;
    @JoinColumn(name = "codi_depa", referencedColumnName = "codi_depa")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Departamentos codiDepa;

    public Solicitudes() {
    }

    public Solicitudes(Integer codiSoli) {
        this.codiSoli = codiSoli;
    }

    public Solicitudes(Integer codiSoli, String descSoli, int codiUsua, int codiUbic, Date fechHoraSoli, int estaSoli) {
        this.codiSoli = codiSoli;
        this.descSoli = descSoli;
        this.codiUsua = codiUsua;
        this.codiUbic = codiUbic;
        this.fechHoraSoli = fechHoraSoli;
        this.estaSoli = estaSoli;
    }

    public Integer getCodiSoli() {
        return codiSoli;
    }

    public void setCodiSoli(Integer codiSoli) {
        this.codiSoli = codiSoli;
    }

    public String getDescSoli() {
        return descSoli;
    }

    public void setDescSoli(String descSoli) {
        this.descSoli = descSoli;
    }

    public int getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(int codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Integer getCodiEnca() {
        return codiEnca;
    }

    public void setCodiEnca(Integer codiEnca) {
        this.codiEnca = codiEnca;
    }

    public Integer getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(Integer codiEqui) {
        this.codiEqui = codiEqui;
    }

    public int getCodiUbic() {
        return codiUbic;
    }

    public void setCodiUbic(int codiUbic) {
        this.codiUbic = codiUbic;
    }

    public Date getFechHoraSoli() {
        return fechHoraSoli;
    }

    public void setFechHoraSoli(Date fechHoraSoli) {
        this.fechHoraSoli = fechHoraSoli;
    }

    public Integer getTiemResoSoli() {
        return tiemResoSoli;
    }

    public void setTiemResoSoli(Integer tiemResoSoli) {
        this.tiemResoSoli = tiemResoSoli;
    }

    public String getPrioSoli() {
        return prioSoli;
    }

    public void setPrioSoli(String prioSoli) {
        this.prioSoli = prioSoli;
    }

    public int getEstaSoli() {
        return estaSoli;
    }

    public void setEstaSoli(int estaSoli) {
        this.estaSoli = estaSoli;
    }

    @XmlTransient
    public List<ResolucionSolicitudes> getResolucionSolicitudesList() {
        return resolucionSolicitudesList;
    }

    public void setResolucionSolicitudesList(List<ResolucionSolicitudes> resolucionSolicitudesList) {
        this.resolucionSolicitudesList = resolucionSolicitudesList;
    }

    @XmlTransient
    public List<ProcesoSolicitudes> getProcesoSolicitudesList() {
        return procesoSolicitudesList;
    }

    public void setProcesoSolicitudesList(List<ProcesoSolicitudes> procesoSolicitudesList) {
        this.procesoSolicitudesList = procesoSolicitudesList;
    }

    public Departamentos getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Departamentos codiDepa) {
        this.codiDepa = codiDepa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiSoli != null ? codiSoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.codiSoli == null && other.codiSoli != null) || (this.codiSoli != null && !this.codiSoli.equals(other.codiSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Solicitudes[ codiSoli=" + codiSoli + " ]";
    }
    
}
