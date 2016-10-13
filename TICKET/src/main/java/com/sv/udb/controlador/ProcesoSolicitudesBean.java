/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ProcesoSolicitudesFacadeLocal;
import com.sv.udb.ejb.SolicitudesFacadeLocal;
import com.sv.udb.modelo.ProcesoSolicitudes;
import com.sv.udb.modelo.Solicitudes;
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
 * @author REGISTRO
 */
@Named(value = "procesoSolicitudesBean")
@ViewScoped
public class ProcesoSolicitudesBean implements Serializable{
    @EJB
    private ProcesoSolicitudesFacadeLocal FCDEProcSoli;
    private ProcesoSolicitudes objeProcSoli;
    private List<ProcesoSolicitudes> listProcSoli;
    private boolean guardar;

    public ProcesoSolicitudes getObjeProcSoli() {
        return objeProcSoli;
    }

    public void setObjeProcSoli(ProcesoSolicitudes objeProcSoli) {
        this.objeProcSoli = objeProcSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<ProcesoSolicitudes> getListProcSoli() {
        return listProcSoli;
    }
    
    /**
     * Creates a new instance of ProcesoSolicitudesBean
     */
    
    public ProcesoSolicitudesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeProcSoli = new ProcesoSolicitudes();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
            {
            Solicitudes soli = new Solicitudes();
            soli.setCodiSoli(SolicitudesBean.codiSoli);
            this.objeProcSoli.setCodiSoli(soli);
            this.objeProcSoli.setEstaProcSoli(true);
            this.objeProcSoli.setFechProcSoli(new Date());
            FCDEProcSoli.create(this.objeProcSoli);
            this.listProcSoli.add(this.objeProcSoli);
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
            this.listProcSoli.remove(this.objeProcSoli); //Limpia el objeto viejo
            FCDEProcSoli.edit(this.objeProcSoli);
            this.listProcSoli.add(this.objeProcSoli); //Agrega el objeto modificado
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
            this.objeProcSoli.setEstaProcSoli(false);
            this.listProcSoli.remove(this.objeProcSoli); //Limpia el objeto viejo
            FCDEProcSoli.edit(this.objeProcSoli);
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
            this.listProcSoli = FCDEProcSoli.findTodo();
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
            this.objeProcSoli = FCDEProcSoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeProcSoli.getCodiProcSoli()) + "')");
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
