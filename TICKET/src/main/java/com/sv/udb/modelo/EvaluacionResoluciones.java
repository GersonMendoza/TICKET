/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gersonfrancisco
 */
@Entity
@Table(name = "evaluacion_resoluciones", catalog = "system_ticket", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluacionResoluciones.findAll", query = "SELECT e FROM EvaluacionResoluciones e"),
    @NamedQuery(name = "EvaluacionResoluciones.findByCodiEvalReso", query = "SELECT e FROM EvaluacionResoluciones e WHERE e.codiEvalReso = :codiEvalReso"),
    @NamedQuery(name = "EvaluacionResoluciones.findByOpinEvalReso", query = "SELECT e FROM EvaluacionResoluciones e WHERE e.opinEvalReso = :opinEvalReso"),
    @NamedQuery(name = "EvaluacionResoluciones.findByFechEvalReso", query = "SELECT e FROM EvaluacionResoluciones e WHERE e.fechEvalReso = :fechEvalReso"),
    @NamedQuery(name = "EvaluacionResoluciones.findByEstaEvalReso", query = "SELECT e FROM EvaluacionResoluciones e WHERE e.estaEvalReso = :estaEvalReso")})
public class EvaluacionResoluciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_eval_reso")
    private Integer codiEvalReso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "opin_eval_reso")
    private String opinEvalReso;
    @Lob
    @Size(max = 65535)
    @Column(name = "obse_eval_reso")
    private String obseEvalReso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_eval_reso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEvalReso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "esta_eval_reso")
    private boolean estaEvalReso;
    @JoinColumn(name = "codi_reso_soli", referencedColumnName = "codi_reso_soli")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ResolucionSolicitudes codiResoSoli;

    public EvaluacionResoluciones() {
    }

    public EvaluacionResoluciones(Integer codiEvalReso) {
        this.codiEvalReso = codiEvalReso;
    }

    public EvaluacionResoluciones(Integer codiEvalReso, String opinEvalReso, Date fechEvalReso, boolean estaEvalReso) {
        this.codiEvalReso = codiEvalReso;
        this.opinEvalReso = opinEvalReso;
        this.fechEvalReso = fechEvalReso;
        this.estaEvalReso = estaEvalReso;
    }

    public Integer getCodiEvalReso() {
        return codiEvalReso;
    }

    public void setCodiEvalReso(Integer codiEvalReso) {
        this.codiEvalReso = codiEvalReso;
    }

    public String getOpinEvalReso() {
        return opinEvalReso;
    }

    public void setOpinEvalReso(String opinEvalReso) {
        this.opinEvalReso = opinEvalReso;
    }

    public String getObseEvalReso() {
        return obseEvalReso;
    }

    public void setObseEvalReso(String obseEvalReso) {
        this.obseEvalReso = obseEvalReso;
    }

    public Date getFechEvalReso() {
        return fechEvalReso;
    }

    public void setFechEvalReso(Date fechEvalReso) {
        this.fechEvalReso = fechEvalReso;
    }

    public boolean getEstaEvalReso() {
        return estaEvalReso;
    }

    public void setEstaEvalReso(boolean estaEvalReso) {
        this.estaEvalReso = estaEvalReso;
    }

    public ResolucionSolicitudes getCodiResoSoli() {
        return codiResoSoli;
    }

    public void setCodiResoSoli(ResolucionSolicitudes codiResoSoli) {
        this.codiResoSoli = codiResoSoli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEvalReso != null ? codiEvalReso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionResoluciones)) {
            return false;
        }
        EvaluacionResoluciones other = (EvaluacionResoluciones) object;
        if ((this.codiEvalReso == null && other.codiEvalReso != null) || (this.codiEvalReso != null && !this.codiEvalReso.equals(other.codiEvalReso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelo.EvaluacionResoluciones[ codiEvalReso=" + codiEvalReso + " ]";
    }
    
}
