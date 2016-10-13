/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ResolucionSolicitudesFacadeLocal;
import com.sv.udb.modelo.ResolucionSolicitudes;
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
@Named(value = "ResolucionSolicitudesBean")
@ViewScoped
public class ResolucionSolicitudesBean implements Serializable {

    @EJB
    private ResolucionSolicitudesFacadeLocal FCDEResoSoli;
    private ResolucionSolicitudes objeResoSoli;
    private List<ResolucionSolicitudes> listResoSoli;
    private boolean guardar;

    public ResolucionSolicitudesFacadeLocal getFCDEResoSoli() {
        return FCDEResoSoli;
    }

    public void setFCDEResoSoli(ResolucionSolicitudesFacadeLocal FCDEResoSoli) {
        this.FCDEResoSoli = FCDEResoSoli;
    }

    public ResolucionSolicitudes getObjeResoSoli() {
        return objeResoSoli;
    }

    public void setObjeResoSoli(ResolucionSolicitudes objeResoSoli) {
        this.objeResoSoli = objeResoSoli;
    }

    public List<ResolucionSolicitudes> getListResoSoli() {
        return listResoSoli;
    }

    public void setListResoSoli(List<ResolucionSolicitudes> listResoSoli) {
        this.listResoSoli = listResoSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    /**
     * Creates a new instance of ResolucionesBean
     */
    public ResolucionSolicitudesBean() {
    }
    
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeResoSoli = new ResolucionSolicitudes();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeResoSoli.setEstaResoSoli(3);
            this.objeResoSoli.setFechResoSoli(new Date());
            FCDEResoSoli.create(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli);
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
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.edit(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli); //Agrega el objeto modificado
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
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.remove(this.objeResoSoli);
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
            this.listResoSoli = FCDEResoSoli.findTodo();
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
            this.objeResoSoli = FCDEResoSoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeResoSoli.getCodiResoSoli()) + "')");
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
