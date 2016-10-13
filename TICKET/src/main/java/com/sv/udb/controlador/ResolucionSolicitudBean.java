/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ResolucionSolicitudesFacadeLocal;
import com.sv.udb.ejb.SolicitudesFacadeLocal;
import com.sv.udb.modelo.ResolucionSolicitudes;
import com.sv.udb.modelo.Solicitudes;
import com.sv.udb.utils.Logs;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "resolucionSolicitudBean")
@ViewScoped
public class ResolucionSolicitudBean implements Serializable{
    @EJB
    private ResolucionSolicitudesFacadeLocal FCDEResoSoli;
    private SolicitudesFacadeLocal FCDESoli; 
    private ResolucionSolicitudes objeResoSoli;
    private List<ResolucionSolicitudes> listResoSoli;
    private boolean guardar;
    private Logs<ResolucionSolicitudBean> lgs = new Logs<ResolucionSolicitudBean>(ResolucionSolicitudBean.class) {
    };
    private Logger log = lgs.getLog();

    public ResolucionSolicitudes getObjeResoSoli() {
        return objeResoSoli;
    }

    public void setObjeResoSoli(ResolucionSolicitudes objeResoSoli) {
        this.objeResoSoli = objeResoSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<ResolucionSolicitudes> getListResoSoli() {
        return listResoSoli;
    }
    
    /**
     * Creates a new instance of ResolucionSolicitudesBean
     */
    
    public ResolucionSolicitudBean() {
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
        this.objeResoSoli = new ResolucionSolicitudes();
        this.guardar = true;        
    }
    public void guar()
    {
        log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            Solicitudes soli = new Solicitudes(SolicitudesBean.codiSoli);
            this.objeResoSoli.setCodiSoli(soli);
            this.objeResoSoli.setFechResoSoli(new Date());
            FCDEResoSoli.create(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli);
            this.limpForm();
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Se han guardado correctamento los datos");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error("Ocurrio un error al momento de guardar");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        log.debug("Se intenda modificar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeResoSoli.setFechResoSoli(new Date());
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.edit(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Los datos se han modificado correctamente en el bean");
        }
        catch(Exception ex)
        {
            log.error("ocurrio un error al momento de modificar");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        log.debug("Se esta intentado eliminar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.remove(this.objeResoSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Los datos se han eliminado correctamente");
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de eliminar");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        log.debug("Se esta intentando consultar todo");
        try
        {
            this.listResoSoli = FCDEResoSoli.findAll();
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de consultar todo");
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeResoSoli = FCDEResoSoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeResoSoli.getCodiResoSoli()) + "')");
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            log.error("Ocurrio un error al momento de consultar");
        }
        finally
        {
            
        }
    }
}
