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
@Table(name = "usuario_rol", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByCodiUsuaRole", query = "SELECT u FROM UsuarioRol u WHERE u.codiUsuaRole = :codiUsuaRole"),
    @NamedQuery(name = "UsuarioRol.findByEstaUsuaRole", query = "SELECT u FROM UsuarioRol u WHERE u.estaUsuaRole = :estaUsuaRole")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_usua_role")
    private Integer codiUsuaRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_usua_role")
    private int estaUsuaRole;
    @JoinColumn(name = "codi_usua", referencedColumnName = "codi_usua")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codiUsua;
    @JoinColumn(name = "codi_role", referencedColumnName = "codi_role")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol codiRole;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer codiUsuaRole) {
        this.codiUsuaRole = codiUsuaRole;
    }

    public UsuarioRol(Integer codiUsuaRole, int estaUsuaRole) {
        this.codiUsuaRole = codiUsuaRole;
        this.estaUsuaRole = estaUsuaRole;
    }

    public Integer getCodiUsuaRole() {
        return codiUsuaRole;
    }

    public void setCodiUsuaRole(Integer codiUsuaRole) {
        this.codiUsuaRole = codiUsuaRole;
    }

    public int getEstaUsuaRole() {
        return estaUsuaRole;
    }

    public void setEstaUsuaRole(int estaUsuaRole) {
        this.estaUsuaRole = estaUsuaRole;
    }

    public Usuario getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Usuario codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Rol getCodiRole() {
        return codiRole;
    }

    public void setCodiRole(Rol codiRole) {
        this.codiRole = codiRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsuaRole != null ? codiUsuaRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.codiUsuaRole == null && other.codiUsuaRole != null) || (this.codiUsuaRole != null && !this.codiUsuaRole.equals(other.codiUsuaRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.UsuarioRol[ codiUsuaRole=" + codiUsuaRole + " ]";
    }
    
}
