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
@Table(name = "rol", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByCodiRole", query = "SELECT r FROM Rol r WHERE r.codiRole = :codiRole"),
    @NamedQuery(name = "Rol.findByNombRole", query = "SELECT r FROM Rol r WHERE r.nombRole = :nombRole"),
    @NamedQuery(name = "Rol.findByDescRole", query = "SELECT r FROM Rol r WHERE r.descRole = :descRole"),
    @NamedQuery(name = "Rol.findByEstaRole", query = "SELECT r FROM Rol r WHERE r.estaRole = :estaRole")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_role")
    private Integer codiRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nomb_role")
    private String nombRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "desc_role")
    private String descRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_role")
    private int estaRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiRole", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiRole", fetch = FetchType.LAZY)
    private List<PermisoRol> permisoRolList;

    public Rol() {
    }

    public Rol(Integer codiRole) {
        this.codiRole = codiRole;
    }

    public Rol(Integer codiRole, String nombRole, String descRole, int estaRole) {
        this.codiRole = codiRole;
        this.nombRole = nombRole;
        this.descRole = descRole;
        this.estaRole = estaRole;
    }

    public Integer getCodiRole() {
        return codiRole;
    }

    public void setCodiRole(Integer codiRole) {
        this.codiRole = codiRole;
    }

    public String getNombRole() {
        return nombRole;
    }

    public void setNombRole(String nombRole) {
        this.nombRole = nombRole;
    }

    public String getDescRole() {
        return descRole;
    }

    public void setDescRole(String descRole) {
        this.descRole = descRole;
    }

    public int getEstaRole() {
        return estaRole;
    }

    public void setEstaRole(int estaRole) {
        this.estaRole = estaRole;
    }

    @XmlTransient
    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    @XmlTransient
    public List<PermisoRol> getPermisoRolList() {
        return permisoRolList;
    }

    public void setPermisoRolList(List<PermisoRol> permisoRolList) {
        this.permisoRolList = permisoRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiRole != null ? codiRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.codiRole == null && other.codiRole != null) || (this.codiRole != null && !this.codiRole.equals(other.codiRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Rol[ codiRole=" + codiRole + " ]";
    }
    
}
