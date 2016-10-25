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
@Table(name = "usuarios", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByCodiUsua", query = "SELECT u FROM Usuarios u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuarios.findByAcceUsua", query = "SELECT u FROM Usuarios u WHERE u.acceUsua = :acceUsua"),
    @NamedQuery(name = "Usuarios.findByContUsua", query = "SELECT u FROM Usuarios u WHERE u.contUsua = :contUsua"),
    @NamedQuery(name = "Usuarios.findByEstaUsua", query = "SELECT u FROM Usuarios u WHERE u.estaUsua = :estaUsua")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_usua")
    private Integer codiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "acce_usua")
    private String acceUsua;
    @Size(max = 100)
    @Column(name = "cont_usua")
    private String contUsua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_usua")
    private int estaUsua;
    @JoinColumn(name = "codi_tipo", referencedColumnName = "codi_tipo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposUsuarios codiTipo;

    public Usuarios() {
    }

    public Usuarios(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuarios(Integer codiUsua, String acceUsua, int estaUsua) {
        this.codiUsua = codiUsua;
        this.acceUsua = acceUsua;
        this.estaUsua = estaUsua;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getAcceUsua() {
        return acceUsua;
    }

    public void setAcceUsua(String acceUsua) {
        this.acceUsua = acceUsua;
    }

    public String getContUsua() {
        return contUsua;
    }

    public void setContUsua(String contUsua) {
        this.contUsua = contUsua;
    }

    public int getEstaUsua() {
        return estaUsua;
    }

    public void setEstaUsua(int estaUsua) {
        this.estaUsua = estaUsua;
    }

    public TiposUsuarios getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(TiposUsuarios codiTipo) {
        this.codiTipo = codiTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Usuarios[ codiUsua=" + codiUsua + " ]";
    }
    
}
