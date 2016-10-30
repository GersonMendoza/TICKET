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
@Table(name = "solicitudes", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s"),
    @NamedQuery(name = "Solicitudes.findByCodiSoli", query = "SELECT s FROM Solicitudes s WHERE s.codiSoli = :codiSoli"),
    @NamedQuery(name = "Solicitudes.findByCodiUsua", query = "SELECT s FROM Solicitudes s WHERE s.codiUsua = :codiUsua"),
    @NamedQuery(name = "Solicitudes.findByCodiEnca", query = "SELECT s FROM Solicitudes s WHERE s.codiEnca = :codiEnca"),
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
    @Column(name = "codi_usua")
    private int codiUsua;
    @Column(name = "codi_enca")
    private Integer codiEnca;
    @Column(name = "fech_hora_soli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechHoraSoli;
    @Column(name = "tiem_reso_soli")
    private Integer tiemResoSoli;
    @Size(max = 50)
    @Column(name = "prio_soli")
    private String prioSoli;
    @Column(name = "esta_soli")
    private Integer estaSoli;
    @Lob
    @Size(max = 65535)
    @Column(name = "desc_soli")
    private String descSoli;
    @JoinColumn(name = "codi_depa", referencedColumnName = "codi_depa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamentos codiDepa;
    @JoinColumn(name = "codi_mant", referencedColumnName = "codi_mant")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mantenimientos codiMant;
    @JoinColumn(name = "codi_ubic", referencedColumnName = "codi_ubic")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ubicaciones codiUbic;

    public Solicitudes() {
    }

    public Solicitudes(Integer codiSoli) {
        this.codiSoli = codiSoli;
    }

    public Solicitudes(Integer codiSoli, int codiUsua) {
        this.codiSoli = codiSoli;
        this.codiUsua = codiUsua;
    }

    public Integer getCodiSoli() {
        return codiSoli;
    }

    public void setCodiSoli(Integer codiSoli) {
        this.codiSoli = codiSoli;
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

    public Integer getEstaSoli() {
        return estaSoli;
    }

    public void setEstaSoli(Integer estaSoli) {
        this.estaSoli = estaSoli;
    }

    public String getDescSoli() {
        return descSoli;
    }

    public void setDescSoli(String descSoli) {
        this.descSoli = descSoli;
    }

    public Departamentos getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Departamentos codiDepa) {
        this.codiDepa = codiDepa;
    }

    public Mantenimientos getCodiMant() {
        return codiMant;
    }

    public void setCodiMant(Mantenimientos codiMant) {
        this.codiMant = codiMant;
    }

    public Ubicaciones getCodiUbic() {
        return codiUbic;
    }

    public void setCodiUbic(Ubicaciones codiUbic) {
        this.codiUbic = codiUbic;
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
