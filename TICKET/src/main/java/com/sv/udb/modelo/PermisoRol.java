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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "permiso_rol", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoRol.findAll", query = "SELECT p FROM PermisoRol p"),
    @NamedQuery(name = "PermisoRol.findByCodiPermRole", query = "SELECT p FROM PermisoRol p WHERE p.codiPermRole = :codiPermRole"),
    @NamedQuery(name = "PermisoRol.findByEstaPermRole", query = "SELECT p FROM PermisoRol p WHERE p.estaPermRole = :estaPermRole")})
public class PermisoRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_perm_role")
    private Integer codiPermRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_perm_role")
    private int estaPermRole;
    @JoinColumn(name = "codi_role", referencedColumnName = "codi_role")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol codiRole;
    @JoinColumn(name = "codi_perm", referencedColumnName = "codi_perm")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permiso codiPerm;

    public PermisoRol() {
    }

    public PermisoRol(Integer codiPermRole) {
        this.codiPermRole = codiPermRole;
    }

    public PermisoRol(Integer codiPermRole, int estaPermRole) {
        this.codiPermRole = codiPermRole;
        this.estaPermRole = estaPermRole;
    }

    public Integer getCodiPermRole() {
        return codiPermRole;
    }

    public void setCodiPermRole(Integer codiPermRole) {
        this.codiPermRole = codiPermRole;
    }

    public int getEstaPermRole() {
        return estaPermRole;
    }

    public void setEstaPermRole(int estaPermRole) {
        this.estaPermRole = estaPermRole;
    }

    public Rol getCodiRole() {
        return codiRole;
    }

    public void setCodiRole(Rol codiRole) {
        this.codiRole = codiRole;
    }

    public Permiso getCodiPerm() {
        return codiPerm;
    }

    public void setCodiPerm(Permiso codiPerm) {
        this.codiPerm = codiPerm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPermRole != null ? codiPermRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoRol)) {
            return false;
        }
        PermisoRol other = (PermisoRol) object;
        if ((this.codiPermRole == null && other.codiPermRole != null) || (this.codiPermRole != null && !this.codiPermRole.equals(other.codiPermRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.PermisoRol[ codiPermRole=" + codiPermRole + " ]";
    }
    
}
