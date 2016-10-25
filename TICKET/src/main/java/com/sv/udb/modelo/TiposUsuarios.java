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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "tipos_usuarios", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposUsuarios.findAll", query = "SELECT t FROM TiposUsuarios t"),
    @NamedQuery(name = "TiposUsuarios.findByCodiTipo", query = "SELECT t FROM TiposUsuarios t WHERE t.codiTipo = :codiTipo"),
    @NamedQuery(name = "TiposUsuarios.findByNombTipo", query = "SELECT t FROM TiposUsuarios t WHERE t.nombTipo = :nombTipo")})
public class TiposUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_tipo")
    private Integer codiTipo;
    @Size(max = 45)
    @Column(name = "nomb_tipo")
    private String nombTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiTipo", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    public TiposUsuarios() {
    }

    public TiposUsuarios(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public Integer getCodiTipo() {
        return codiTipo;
    }

    public void setCodiTipo(Integer codiTipo) {
        this.codiTipo = codiTipo;
    }

    public String getNombTipo() {
        return nombTipo;
    }

    public void setNombTipo(String nombTipo) {
        this.nombTipo = nombTipo;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiTipo != null ? codiTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposUsuarios)) {
            return false;
        }
        TiposUsuarios other = (TiposUsuarios) object;
        if ((this.codiTipo == null && other.codiTipo != null) || (this.codiTipo != null && !this.codiTipo.equals(other.codiTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.TiposUsuarios[ codiTipo=" + codiTipo + " ]";
    }
    
}
