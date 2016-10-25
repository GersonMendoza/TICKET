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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "equipos", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipos.findAll", query = "SELECT e FROM Equipos e"),
    @NamedQuery(name = "Equipos.findByCodiEqui", query = "SELECT e FROM Equipos e WHERE e.codiEqui = :codiEqui"),
    @NamedQuery(name = "Equipos.findByMarEqui", query = "SELECT e FROM Equipos e WHERE e.marEqui = :marEqui"),
    @NamedQuery(name = "Equipos.findByModeEqui", query = "SELECT e FROM Equipos e WHERE e.modeEqui = :modeEqui"),
    @NamedQuery(name = "Equipos.findBySeriEqui", query = "SELECT e FROM Equipos e WHERE e.seriEqui = :seriEqui"),
    @NamedQuery(name = "Equipos.findByFechGaraEqui", query = "SELECT e FROM Equipos e WHERE e.fechGaraEqui = :fechGaraEqui")})
public class Equipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_equi")
    private Integer codiEqui;
    @Lob
    @Size(max = 65535)
    @Column(name = "desc_equi")
    private String descEqui;
    @Lob
    @Size(max = 65535)
    @Column(name = "cara_equi")
    private String caraEqui;
    @Size(max = 45)
    @Column(name = "mar_equi")
    private String marEqui;
    @Size(max = 45)
    @Column(name = "mode_equi")
    private String modeEqui;
    @Size(max = 45)
    @Column(name = "seri_equi")
    private String seriEqui;
    @Size(max = 45)
    @Column(name = "fech_gara_equi")
    private String fechGaraEqui;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiEqui", fetch = FetchType.LAZY)
    private List<EquiposSolicitudes> equiposSolicitudesList;
    @JoinColumn(name = "codi_ubi", referencedColumnName = "codi_ubi")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ubicaciones codiUbi;

    public Equipos() {
    }

    public Equipos(Integer codiEqui) {
        this.codiEqui = codiEqui;
    }

    public Integer getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(Integer codiEqui) {
        this.codiEqui = codiEqui;
    }

    public String getDescEqui() {
        return descEqui;
    }

    public void setDescEqui(String descEqui) {
        this.descEqui = descEqui;
    }

    public String getCaraEqui() {
        return caraEqui;
    }

    public void setCaraEqui(String caraEqui) {
        this.caraEqui = caraEqui;
    }

    public String getMarEqui() {
        return marEqui;
    }

    public void setMarEqui(String marEqui) {
        this.marEqui = marEqui;
    }

    public String getModeEqui() {
        return modeEqui;
    }

    public void setModeEqui(String modeEqui) {
        this.modeEqui = modeEqui;
    }

    public String getSeriEqui() {
        return seriEqui;
    }

    public void setSeriEqui(String seriEqui) {
        this.seriEqui = seriEqui;
    }

    public String getFechGaraEqui() {
        return fechGaraEqui;
    }

    public void setFechGaraEqui(String fechGaraEqui) {
        this.fechGaraEqui = fechGaraEqui;
    }

    @XmlTransient
    public List<EquiposSolicitudes> getEquiposSolicitudesList() {
        return equiposSolicitudesList;
    }

    public void setEquiposSolicitudesList(List<EquiposSolicitudes> equiposSolicitudesList) {
        this.equiposSolicitudesList = equiposSolicitudesList;
    }

    public Ubicaciones getCodiUbi() {
        return codiUbi;
    }

    public void setCodiUbi(Ubicaciones codiUbi) {
        this.codiUbi = codiUbi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEqui != null ? codiEqui.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipos)) {
            return false;
        }
        Equipos other = (Equipos) object;
        if ((this.codiEqui == null && other.codiEqui != null) || (this.codiEqui != null && !this.codiEqui.equals(other.codiEqui))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Equipos[ codiEqui=" + codiEqui + " ]";
    }
    
}
