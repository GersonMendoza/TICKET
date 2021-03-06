/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.TipoMantenimientosFacadeLocal;
import com.sv.udb.modelo.TipoMantenimientos;
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
 * @author joseph
 */
@Named(value = "tipoMantenimientosBean")
@ViewScoped
public class TipoMantenimientosBean implements Serializable {

    @EJB
    private TipoMantenimientosFacadeLocal FCDETipoMant;
    private TipoMantenimientos objeTipoMant;
    private List<TipoMantenimientos> listTipoMant;
    private boolean guardar;
    private Logs<TipoMantenimientosBean> lgs = new Logs<TipoMantenimientosBean>(TipoMantenimientosBean.class) {
    };
    private Logger log = lgs.getLog();

    public TipoMantenimientosFacadeLocal getFCDETipoMant() {
        return FCDETipoMant;
    }

    public void setFCDETipoMant(TipoMantenimientosFacadeLocal FCDETipoMant) {
        this.FCDETipoMant = FCDETipoMant;
    }

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
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log.debug("Se ha inicializado el bean");
    }
    
    public void limpForm()
    {
        this.objeTipoMant = new TipoMantenimientos();
        this.guardar = true;        
    }
    
    public void guar()
    {
         log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeTipoMant.setEstaTipoMant(true);
            this.objeTipoMant.setFechIngrTipoMant(new Date());
            FCDETipoMant.create(this.objeTipoMant);
            this.listTipoMant.add(this.objeTipoMant);
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Se han guardado correctamento los datos");
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de guardar");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
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
            this.listTipoMant.remove(this.objeTipoMant); //Limpia el objeto viejo
            FCDETipoMant.edit(this.objeTipoMant);
            this.listTipoMant.add(this.objeTipoMant); //Agrega el objeto modificado
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
            this.objeTipoMant.setFechBajaTipoMant(new Date());
            this.objeTipoMant.setEstaTipoMant(false);
            this.listTipoMant.remove(this.objeTipoMant); //Limpia el objeto viejo
            FCDETipoMant.edit(this.objeTipoMant);
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
            this.listTipoMant = FCDETipoMant.findTodo();
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
    
    public void cons()
    {
        log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeTipoMant = FCDETipoMant.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipoMant.getNombTipoMant()) + "')");
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
