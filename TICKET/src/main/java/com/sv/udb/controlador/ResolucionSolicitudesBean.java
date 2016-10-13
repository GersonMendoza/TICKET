/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ResolucionSolicitudesFacadeLocal;
import com.sv.udb.modelo.ResolucionSolicitudes;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alexander
 */
@Named(value = "resolucionSolicitudesBean")
@ViewScoped
public class ResolucionSolicitudesBean implements Serializable{

    @EJB
    private ResolucionSolicitudesFacadeLocal FCDEResoSoli;
    private ResolucionSolicitudes objeResoSoli;
    private List<ResolucionSolicitudes> listResoSoliUsua;//Lista usada para almacenar las resoluciones que corresponden a las solicitudes del usuario que ha iniciado sesión
    private boolean guardar;

    public ResolucionSolicitudes getObjeResoSoli() {
        return objeResoSoli;
    }

    public void setObjeResoSoli(ResolucionSolicitudes objeResoSoli) {
        this.objeResoSoli = objeResoSoli;
    }

    public List<ResolucionSolicitudes> getListResoSoliUsua() {
        return listResoSoliUsua;
    }

    public void setListResoSoliUsua(List<ResolucionSolicitudes> listResoSoliUsua) {
        this.listResoSoliUsua = listResoSoliUsua;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    /**
     * Creates a new instance of ResolucionSolicitudesBean
     */
    public ResolucionSolicitudesBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeResoSoli = new ResolucionSolicitudes();
        this.guardar = true;
    }

    public void consTodo() {
        try {
            this.listResoSoliUsua = FCDEResoSoli.findTodoUsua();//Esta función carga todas las resoluciones que corresponden a las solicitudes del usuario que ha iniciado sesión
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
