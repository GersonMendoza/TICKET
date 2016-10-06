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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "mantenimientos", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimientos.findAll", query = "SELECT m FROM Mantenimientos m"),
    @NamedQuery(name = "Mantenimientos.findByCodiMant", query = "SELECT m FROM Mantenimientos m WHERE m.codiMant = :codiMant"),
    @NamedQuery(name = "Mantenimientos.findByContDiasMant", query = "SELECT m FROM Mantenimientos m WHERE m.contDiasMant = :contDiasMant"),
    @NamedQuery(name = "Mantenimientos.findByCodiUbi", query = "SELECT m FROM Mantenimientos m WHERE m.codiUbi = :codiUbi"),
    @NamedQuery(name = "Mantenimientos.findByEstaMantPrev", query = "SELECT m FROM Mantenimientos m WHERE m.estaMantPrev = :estaMantPrev")})
public class Mantenimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_mant")
    private Integer codiMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cont_dias_mant")
    private int contDiasMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codi_ubi")
    private int codiUbi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_mant_prev")
    private boolean estaMantPrev;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiMant", fetch = FetchType.LAZY)
    private List<CorrelativoMantenimientos> correlativoMantenimientosList;
    @JoinColumn(name = "codi_depa", referencedColumnName = "codi_depa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamentos codiDepa;
    @JoinColumn(name = "codi_tipo_mant", referencedColumnName = "codi_tipo_mant")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoMantenimientos codiTipoMant;

    public Mantenimientos() {
    }

    public Mantenimientos(Integer codiMant) {
        this.codiMant = codiMant;
    }

    public Mantenimientos(Integer codiMant, int contDiasMant, int codiUbi, boolean estaMantPrev) {
        this.codiMant = codiMant;
        this.contDiasMant = contDiasMant;
        this.codiUbi = codiUbi;
        this.estaMantPrev = estaMantPrev;
    }

    public Integer getCodiMant() {
        return codiMant;
    }

    public void setCodiMant(Integer codiMant) {
        this.codiMant = codiMant;
    }

    public int getContDiasMant() {
        return contDiasMant;
    }

    public void setContDiasMant(int contDiasMant) {
        this.contDiasMant = contDiasMant;
    }

    public int getCodiUbi() {
        return codiUbi;
    }

    public void setCodiUbi(int codiUbi) {
        this.codiUbi = codiUbi;
    }

    public boolean getEstaMantPrev() {
        return estaMantPrev;
    }

    public void setEstaMantPrev(boolean estaMantPrev) {
        this.estaMantPrev = estaMantPrev;
    }

    @XmlTransient
    public List<CorrelativoMantenimientos> getCorrelativoMantenimientosList() {
        return correlativoMantenimientosList;
    }

    public void setCorrelativoMantenimientosList(List<CorrelativoMantenimientos> correlativoMantenimientosList) {
        this.correlativoMantenimientosList = correlativoMantenimientosList;
    }

    public Departamentos getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Departamentos codiDepa) {
        this.codiDepa = codiDepa;
    }

    public TipoMantenimientos getCodiTipoMant() {
        return codiTipoMant;
    }

    public void setCodiTipoMant(TipoMantenimientos codiTipoMant) {
        this.codiTipoMant = codiTipoMant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMant != null ? codiMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimientos)) {
            return false;
        }
        Mantenimientos other = (Mantenimientos) object;
        if ((this.codiMant == null && other.codiMant != null) || (this.codiMant != null && !this.codiMant.equals(other.codiMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Mantenimientos[ codiMant=" + codiMant + " ]";
    }
    
}
