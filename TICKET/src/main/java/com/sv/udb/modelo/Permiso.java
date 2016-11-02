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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "permiso", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findByCodiPerm", query = "SELECT p FROM Permiso p WHERE p.codiPerm = :codiPerm"),
    @NamedQuery(name = "Permiso.findByDescPerm", query = "SELECT p FROM Permiso p WHERE p.descPerm = :descPerm"),
    @NamedQuery(name = "Permiso.findByDirePerm", query = "SELECT p FROM Permiso p WHERE p.direPerm = :direPerm"),
    @NamedQuery(name = "Permiso.findByEstaPerm", query = "SELECT p FROM Permiso p WHERE p.estaPerm = :estaPerm")})
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_perm")
    private Integer codiPerm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "desc_perm")
    private String descPerm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "dire_perm")
    private String direPerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_perm")
    private int estaPerm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiPerm", fetch = FetchType.LAZY)
    private List<PermisoRol> permisoRolList;
    @OneToMany(mappedBy = "refePerm", fetch = FetchType.LAZY)
    private List<Permiso> permisoList;
    @JoinColumn(name = "refe_perm", referencedColumnName = "codi_perm")
    @ManyToOne(fetch = FetchType.LAZY)
    private Permiso refePerm;

    public Permiso() {
    }

    public Permiso(Integer codiPerm) {
        this.codiPerm = codiPerm;
    }

    public Permiso(Integer codiPerm, String descPerm, String direPerm, int estaPerm) {
        this.codiPerm = codiPerm;
        this.descPerm = descPerm;
        this.direPerm = direPerm;
        this.estaPerm = estaPerm;
    }

    public Integer getCodiPerm() {
        return codiPerm;
    }

    public void setCodiPerm(Integer codiPerm) {
        this.codiPerm = codiPerm;
    }

    public String getDescPerm() {
        return descPerm;
    }

    public void setDescPerm(String descPerm) {
        this.descPerm = descPerm;
    }

    public String getDirePerm() {
        return direPerm;
    }

    public void setDirePerm(String direPerm) {
        this.direPerm = direPerm;
    }

    public int getEstaPerm() {
        return estaPerm;
    }

    public void setEstaPerm(int estaPerm) {
        this.estaPerm = estaPerm;
    }

    @XmlTransient
    public List<PermisoRol> getPermisoRolList() {
        return permisoRolList;
    }

    public void setPermisoRolList(List<PermisoRol> permisoRolList) {
        this.permisoRolList = permisoRolList;
    }

    @XmlTransient
    public List<Permiso> getPermisoList() {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList) {
        this.permisoList = permisoList;
    }

    public Permiso getRefePerm() {
        return refePerm;
    }

    public void setRefePerm(Permiso refePerm) {
        this.refePerm = refePerm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPerm != null ? codiPerm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.codiPerm == null && other.codiPerm != null) || (this.codiPerm != null && !this.codiPerm.equals(other.codiPerm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Permiso[ codiPerm=" + codiPerm + " ]";
    }
    
}
