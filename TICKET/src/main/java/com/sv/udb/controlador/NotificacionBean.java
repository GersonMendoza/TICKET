/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.NotificacionFacadeLocal;
import com.sv.udb.modelo.Notificacion;
import com.sv.udb.modelo.Usuario;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fic
 */

@Named(value = "notificacionBean")
@ViewScoped
@ManagedBean
public class NotificacionBean implements Serializable{
    
    /**
     * Creates a new instance of NotificacionBean
     */
    @EJB
    private NotificacionFacadeLocal FCDENotificacion;
    
    private List<Notificacion> listNoti;
    private Notificacion objeNoti;
    private boolean guardar;
    private LOG4J log;
    
    public NotificacionFacadeLocal getFCDENotificacion() {
        return FCDENotificacion;
    }

    public void setFCDENotificacion(NotificacionFacadeLocal FCDENotificacion) {
        this.FCDENotificacion = FCDENotificacion;
    }

    public List<Notificacion> getListNoti() {
        return listNoti;
    }

    public void setListNoti(List<Notificacion> listNoti) {
        this.listNoti = listNoti;
    }

    public Notificacion getObjeNoti() {
        return objeNoti;
    }

    public void setObjeNoti(Notificacion objeNoti) {
        this.objeNoti = objeNoti;
    }

    public boolean isGuardar() {
        return guardar;
    }

    
    public NotificacionBean() {
    }
    
    /**
     * Método que se ejecuta después de haber construido la clase e inicializa las variables
     */
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        //log = new LOG4J();
        //log.debug("Se inicializa el modelo de Notificacion");
    }
    /**
     * Método que limpia el formulario reiniciando las variables
     */
    public void limpForm()
    {
        this.objeNoti = new Notificacion();
        this.guardar = true;        
    }
    
    /**
     * Método que guarda la información en la base datos
     */
    public void guar(Usuario codi, String mens, String modu, String path)
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            System.out.println("llego");
            this.objeNoti.setCodiUsua(codi);
            this.objeNoti.setMensNoti(mens);
            this.objeNoti.setModuNoti(modu);
            this.objeNoti.setPathNoti(path);
            this.objeNoti.setEstaNoti(1);
            System.out.println("codi noti:" + this.objeNoti.getCodiUsua());
             System.out.println("mens noti:" + this.objeNoti.getMensNoti());
            FCDENotificacion.create(this.objeNoti);
            this.listNoti.add(this.objeNoti);
            this.guardar = false;
            //log.info("Notificación: "+this.objeNoti.getMensNoti());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            //log.error("Error creando pagina: "+getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    /**
     * Método que modifica la información en la base datos
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); 
        try
        {
            this.listNoti.remove(this.objeNoti); //Limpia el objeto viejo
            FCDENotificacion.edit(this.objeNoti);
            this.listNoti.add(this.objeNoti); //Agrega el objeto modificado
            //log.info("Notificacion modificada: "+this.objeNoti.getCodiNoti());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            //log.error("Error modificando pagina: "+getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    /**
     * Método que consulta la información de la base datos
     */
    public void consTodo()
    {
        try
        {
            this.listNoti = FCDENotificacion.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    /**
     * Método que guarda la información de un registro específico de la base datos
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); 
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiObjePara"));
        try
        {
            this.objeNoti = FCDENotificacion.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " +this.objeNoti.getMensNoti()+ "')");
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
