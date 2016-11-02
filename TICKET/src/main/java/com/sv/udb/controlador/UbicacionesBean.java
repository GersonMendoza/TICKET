/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.TipoMantenimientosFacadeLocal;
import com.sv.udb.ejb.UbicacionesFacadeLocal;
import com.sv.udb.modelo.Ubicaciones;
import com.sv.udb.utils.Logs;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.apache.log4j.Logger;

/**
 *
 * @author gersonfrancisco
 */
@Named(value = "ubicacionesBean")
@Dependent
public class UbicacionesBean {
    @EJB
    private UbicacionesFacadeLocal FCDEUbic;
    private Ubicaciones objeUbic;
    private List<Ubicaciones> listUbic;
    private boolean guardar;
    private Logs<UbicacionesBean> lgs = new Logs<UbicacionesBean>(UbicacionesBean.class) {
    };
    private Logger log = lgs.getLog();

    public UbicacionesFacadeLocal getFCDEUbic() {
        return FCDEUbic;
    }

    public void setFCDEUbic(UbicacionesFacadeLocal FCDEUbic) {
        this.FCDEUbic = FCDEUbic;
    }

    public Ubicaciones getObjeUbic() {
        return objeUbic;
    }

    public void setObjeUbic(Ubicaciones objeUbic) {
        this.objeUbic = objeUbic;
    }

    public List<Ubicaciones> getListUbic() {
        return listUbic;
    }

    public void setListUbic(List<Ubicaciones> listUbic) {
        this.listUbic = listUbic;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public UbicacionesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log.debug("Se ha inicializado el bean");
    }
    
    public void limpForm()
    {
        this.objeUbic = new Ubicaciones();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        log.debug("Se esta intentando consultar todo");
        try
        {
            this.listUbic = FCDEUbic.findAll();
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar todo");
        }
        finally
        {
            
        }
    }
    
    public List<Ubicaciones> ubicMantSoli(int codi){
        log.debug("Se esta intentando consultar ubicaciones");
        try
        {
            this.listUbic = FCDEUbic.findUbicMantSoli(codi);
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar ubicaciones");
        }
        return this.listUbic;
    }
}
