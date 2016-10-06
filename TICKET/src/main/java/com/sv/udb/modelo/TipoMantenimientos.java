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
import javax.persistence.Lob;
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
@Table(name = "tipo_mantenimientos", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMantenimientos.findAll", query = "SELECT t FROM TipoMantenimientos t"),
    @NamedQuery(name = "TipoMantenimientos.findByCodiTipoMant", query = "SELECT t FROM TipoMantenimientos t WHERE t.codiTipoMant = :codiTipoMant"),
    @NamedQuery(name = "TipoMantenimientos.findByNombTipoMant", query = "SELECT t FROM TipoMantenimientos t WHERE t.nombTipoMant = :nombTipoMant"),
    @NamedQuery(name = "TipoMantenimientos.findByFechIngrTipoMant", query = "SELECT t FROM TipoMantenimientos t WHERE t.fechIngrTipoMant = :fechIngrTipoMant"),
    @NamedQuery(name = "TipoMantenimientos.findByFechBajaTipoMant", query = "SELECT t FROM TipoMantenimientos t WHERE t.fechBajaTipoMant = :fechBajaTipoMant"),
    @NamedQuery(name = "TipoMantenimientos.findByEstaTipoMant", query = "SELECT t FROM TipoMantenimientos t WHERE t.estaTipoMant = :estaTipoMant")})
public class TipoMantenimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo_mant")
    private Integer codiTipoMant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomb_tipo_mant")
    private String nombTipoMant;
    @Lob
    @Size(max = 65535)
    @Column(name = "desc_tipo_mant")
    private String descTipoMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_ingr_tipo_mant")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechIngrTipoMant;
    @Column(name = "fech_baja_tipo_mant")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechBajaTipoMant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_tipo_mant")
    private boolean estaTipoMant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTipoMant", fetch = FetchType.LAZY)
    private List<Mantenimientos> mantenimientosList;

    public TipoMantenimientos() {
    }

    public TipoMantenimientos(Integer codiTipoMant) {
        this.codiTipoMant = codiTipoMant;
    }

    public TipoMantenimientos(Integer codiTipoMant, String nombTipoMant, Date fechIngrTipoMant, boolean estaTipoMant) {
        this.codiTipoMant = codiTipoMant;
        this.nombTipoMant = nombTipoMant;
        this.fechIngrTipoMant = fechIngrTipoMant;
        this.estaTipoMant = estaTipoMant;
    }

    public Integer getCodiTipoMant() {
        return codiTipoMant;
    }

    public void setCodiTipoMant(Integer codiTipoMant) {
        this.codiTipoMant = codiTipoMant;
    }

    public String getNombTipoMant() {
        return nombTipoMant;
    }

    public void setNombTipoMant(String nombTipoMant) {
        this.nombTipoMant = nombTipoMant;
    }

    public String getDescTipoMant() {
        return descTipoMant;
    }

    public void setDescTipoMant(String descTipoMant) {
        this.descTipoMant = descTipoMant;
    }

    public Date getFechIngrTipoMant() {
        return fechIngrTipoMant;
    }

    public void setFechIngrTipoMant(Date fechIngrTipoMant) {
        this.fechIngrTipoMant = fechIngrTipoMant;
    }

    public Date getFechBajaTipoMant() {
        return fechBajaTipoMant;
    }

    public void setFechBajaTipoMant(Date fechBajaTipoMant) {
        this.fechBajaTipoMant = fechBajaTipoMant;
    }

    public boolean getEstaTipoMant() {
        return estaTipoMant;
    }

    public void setEstaTipoMant(boolean estaTipoMant) {
        this.estaTipoMant = estaTipoMant;
    }

    @XmlTransient
    public List<Mantenimientos> getMantenimientosList() {
        return mantenimientosList;
    }

    public void setMantenimientosList(List<Mantenimientos> mantenimientosList) {
        this.mantenimientosList = mantenimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipoMant != null ? codiTipoMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMantenimientos)) {
            return false;
        }
        TipoMantenimientos other = (TipoMantenimientos) object;
        if ((this.codiTipoMant == null && other.codiTipoMant != null) || (this.codiTipoMant != null && !this.codiTipoMant.equals(other.codiTipoMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TipoMantenimientos[ codiTipoMant=" + codiTipoMant + " ]";
    }
    
}
