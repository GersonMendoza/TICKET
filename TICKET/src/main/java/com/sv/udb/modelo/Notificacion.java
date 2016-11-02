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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "notificacion", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByCodiNoti", query = "SELECT n FROM Notificacion n WHERE n.codiNoti = :codiNoti"),
    @NamedQuery(name = "Notificacion.findByMensNoti", query = "SELECT n FROM Notificacion n WHERE n.mensNoti = :mensNoti"),
    @NamedQuery(name = "Notificacion.findByEstaNoti", query = "SELECT n FROM Notificacion n WHERE n.estaNoti = :estaNoti"),
    @NamedQuery(name = "Notificacion.findByPathNoti", query = "SELECT n FROM Notificacion n WHERE n.pathNoti = :pathNoti"),
    @NamedQuery(name = "Notificacion.findByModuNoti", query = "SELECT n FROM Notificacion n WHERE n.moduNoti = :moduNoti")})
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_noti")
    private Integer codiNoti;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "mens_noti")
    private String mensNoti;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_noti")
    private int estaNoti;
    @Size(max = 150)
    @Column(name = "path_noti")
    private String pathNoti;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "modu_noti")
    private String moduNoti;
    @JoinColumn(name = "codi_usua", referencedColumnName = "codi_usua")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codiUsua;

    public Notificacion() {
    }

    public Notificacion(Integer codiNoti) {
        this.codiNoti = codiNoti;
    }

    public Notificacion(Integer codiNoti, String mensNoti, int estaNoti, String moduNoti) {
        this.codiNoti = codiNoti;
        this.mensNoti = mensNoti;
        this.estaNoti = estaNoti;
        this.moduNoti = moduNoti;
    }

    public Integer getCodiNoti() {
        return codiNoti;
    }

    public void setCodiNoti(Integer codiNoti) {
        this.codiNoti = codiNoti;
    }

    public String getMensNoti() {
        return mensNoti;
    }

    public void setMensNoti(String mensNoti) {
        this.mensNoti = mensNoti;
    }

    public int getEstaNoti() {
        return estaNoti;
    }

    public void setEstaNoti(int estaNoti) {
        this.estaNoti = estaNoti;
    }

    public String getPathNoti() {
        return pathNoti;
    }

    public void setPathNoti(String pathNoti) {
        this.pathNoti = pathNoti;
    }

    public String getModuNoti() {
        return moduNoti;
    }

    public void setModuNoti(String moduNoti) {
        this.moduNoti = moduNoti;
    }

    public Usuario getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Usuario codiUsua) {
        this.codiUsua = codiUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiNoti != null ? codiNoti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.codiNoti == null && other.codiNoti != null) || (this.codiNoti != null && !this.codiNoti.equals(other.codiNoti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Notificacion[ codiNoti=" + codiNoti + " ]";
    }
    
}
