/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EquiposSolicitudesFacadeLocal;
import com.sv.udb.modelo.Equipos;
import com.sv.udb.utils.Logs;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alexander
 */
@Named(value = "equiposSolicitudesBean")
@ViewScoped
public class EquiposSolicitudesBean implements Serializable{
    
    @EJB
    private EquiposSolicitudesFacadeLocal FCDEEquiSoli;
    private List<Equipos> listEqui;
    private Logs<EquiposSolicitudesBean> lgs = new Logs<EquiposSolicitudesBean>(EquiposSolicitudesBean.class) {
    };
    private Logger log = lgs.getLog();

    public List<Equipos> getListEqui() {
        return listEqui;
    }

    public void setListEqui(List<Equipos> listEqui) {
        this.listEqui = listEqui;
    }
    
    /**
     * Creates a new instance of EquiposSolicitudesBean
     */
    public EquiposSolicitudesBean() {
    }
    
    public Long cantEqui(int codi){
        log.debug("Se intenta consultar cantidad de equipos");
        long cant = -1;
        try
        {
            cant = FCDEEquiSoli.cantEqui(codi);
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar");
        }
        return cant;
    }
    
    public List<Equipos> equi(int codi){
        log.debug("Se intenta consultar equipos");
        try
        {
            this.listEqui = FCDEEquiSoli.findTodo(codi);
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar");
        }
        return this.listEqui;
    }
    
    public String desc(int codiEqui, int codiSoli){
        log.debug("Se intenta consultar descripción");
        String desc = "";
        try
        {
            desc = FCDEEquiSoli.findDesc(codiEqui, codiSoli);
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar");
        }
        return desc;
    }  
}
