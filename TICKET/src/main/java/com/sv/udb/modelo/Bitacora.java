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
@Table(name = "bitacora", catalog = "sistemas_pilet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByCodiBita", query = "SELECT b FROM Bitacora b WHERE b.codiBita = :codiBita"),
    @NamedQuery(name = "Bitacora.findByNiveBita", query = "SELECT b FROM Bitacora b WHERE b.niveBita = :niveBita"),
    @NamedQuery(name = "Bitacora.findByLoggBita", query = "SELECT b FROM Bitacora b WHERE b.loggBita = :loggBita"),
    @NamedQuery(name = "Bitacora.findByNombBita", query = "SELECT b FROM Bitacora b WHERE b.nombBita = :nombBita"),
    @NamedQuery(name = "Bitacora.findByAcciBita", query = "SELECT b FROM Bitacora b WHERE b.acciBita = :acciBita"),
    @NamedQuery(name = "Bitacora.findByFechBita", query = "SELECT b FROM Bitacora b WHERE b.fechBita = :fechBita")})
public class Bitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_bita")
    private Integer codiBita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nive_bita")
    private String niveBita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "logg_bita")
    private String loggBita;
    @Size(max = 100)
    @Column(name = "nomb_bita")
    private String nombBita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "acci_bita")
    private String acciBita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fech_bita")
    private String fechBita;
    @JoinColumn(name = "codi_usua", referencedColumnName = "codi_usua")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario codiUsua;

    public Bitacora() {
    }

    public Bitacora(Integer codiBita) {
        this.codiBita = codiBita;
    }

    public Bitacora(Integer codiBita, String niveBita, String loggBita, String acciBita, String fechBita) {
        this.codiBita = codiBita;
        this.niveBita = niveBita;
        this.loggBita = loggBita;
        this.acciBita = acciBita;
        this.fechBita = fechBita;
    }

    public Integer getCodiBita() {
        return codiBita;
    }

    public void setCodiBita(Integer codiBita) {
        this.codiBita = codiBita;
    }

    public String getNiveBita() {
        return niveBita;
    }

    public void setNiveBita(String niveBita) {
        this.niveBita = niveBita;
    }

    public String getLoggBita() {
        return loggBita;
    }

    public void setLoggBita(String loggBita) {
        this.loggBita = loggBita;
    }

    public String getNombBita() {
        return nombBita;
    }

    public void setNombBita(String nombBita) {
        this.nombBita = nombBita;
    }

    public String getAcciBita() {
        return acciBita;
    }

    public void setAcciBita(String acciBita) {
        this.acciBita = acciBita;
    }

    public String getFechBita() {
        return fechBita;
    }

    public void setFechBita(String fechBita) {
        this.fechBita = fechBita;
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
        hash += (codiBita != null ? codiBita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.codiBita == null && other.codiBita != null) || (this.codiBita != null && !this.codiBita.equals(other.codiBita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.Bitacora[ codiBita=" + codiBita + " ]";
    }
    
}
