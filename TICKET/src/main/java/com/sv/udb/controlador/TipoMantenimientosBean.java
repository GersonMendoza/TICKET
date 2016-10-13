/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.TipoMantenimientosFacadeLocal;
import com.sv.udb.modelo.TipoMantenimientos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
    }
    
    public void limpForm()
    {
        this.objeTipoMant = new TipoMantenimientos();
        this.guardar = true;        
    }
    
    public void guar()
    {
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
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listTipoMant.remove(this.objeTipoMant); //Limpia el objeto viejo
            FCDETipoMant.edit(this.objeTipoMant);
            this.listTipoMant.add(this.objeTipoMant); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeTipoMant.setFechBajaTipoMant(new Date());
            this.objeTipoMant.setEstaTipoMant(false);
            this.listTipoMant.remove(this.objeTipoMant); //Limpia el objeto viejo
            FCDETipoMant.edit(this.objeTipoMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listTipoMant = FCDETipoMant.findTodo();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeTipoMant = FCDETipoMant.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeTipoMant.getNombTipoMant()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
}
