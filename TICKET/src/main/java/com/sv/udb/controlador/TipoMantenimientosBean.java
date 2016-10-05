/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.TipoMantenimientosFacadeLocal;
import com.sv.udb.modelo.TipoMantenimientos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Alexander
 */
@Named(value = "tipoMantenimientosBean")
@RequestScoped
public class TipoMantenimientosBean implements Serializable {

    @EJB
    private TipoMantenimientosFacadeLocal FCDETipoMant;
    private TipoMantenimientos objeTipoMant;
    private List<TipoMantenimientos> listTipoMant;
    private boolean guardar;

    public TipoMantenimientos getObjeTipoMant() {
        return objeTipoMant;
    }

    public void setObjeTipoMant(TipoMantenimientos objeTipoMant) {
        this.objeTipoMant = objeTipoMant;
    }

    public List<TipoMantenimientos> getListTipoMant() {
        return listTipoMant;
    }

    public void setListTipoMant(List<TipoMantenimientos> listTipoMant) {
        this.listTipoMant = listTipoMant;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    /**
     * Creates a new instance of TipoMantenimientosBean
     */
    public TipoMantenimientosBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeTipoMant = new TipoMantenimientos();
        this.guardar = true;
    }
    
    public void consTodo() {
        try {
            this.listTipoMant = FCDETipoMant.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
