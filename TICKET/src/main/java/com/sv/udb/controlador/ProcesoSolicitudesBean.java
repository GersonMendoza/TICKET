/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ProcesoSolicitudesFacadeLocal;
import com.sv.udb.modelo.ProcesoSolicitudes;
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
 * Esta clase se encuentran los metodos para el manejo de los datos (CRUD) del objeto objeProcSoli
 * @author Mulato
 * @version 1.2
 */
@Named(value = "procesoSolicitudesBean")
@ViewScoped
public class ProcesoSolicitudesBean implements Serializable{
    @EJB
    private ProcesoSolicitudesFacadeLocal FCDEProcSoli;
    private ProcesoSolicitudes objeProcSoli;
    private List<ProcesoSolicitudes> listProcSoli;
    private boolean guardar;
    private Logs<ProcesoSolicitudesBean> lgs = new Logs<ProcesoSolicitudesBean>(ProcesoSolicitudesBean.class) {
    };
    private Logger log = lgs.getLog();

    /**
     * Función para obtener  el objeto objeProcSoli
     * @return ProcesoSolicitudes objeProcSoli
     */
    public ProcesoSolicitudes getObjeProcSoli() {
        return objeProcSoli;
    }

    /**
     * Función para definir el objeto objeProcSoli
     * @param objeProcSoli
     */
    public void setObjeProcSoli(ProcesoSolicitudes objeProcSoli) {
        this.objeProcSoli = objeProcSoli;
    }

    /**
     * Función que retorna el valor de la variable guardar para saber si se está guardando o no actualmente
     * @return Boolean guardar
     */
    public boolean isGuardar() {
        return guardar;
    }

    /**
     * Función que retorna la lista de objetos de ProcesoSolicitudes
     * @return List listProcSoli
     */
    public List<ProcesoSolicitudes> getListProcSoli() {
        return listProcSoli;
    }
    
    /**
     * Creates a new instance of ProcesoSolicitudesBean
     */
    
    public ProcesoSolicitudesBean() {
    }
    
    /**
     * Función que se ejecuta después de construir la clase
     */
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        log.debug("Se ha inicializado el bean");
    }
    
    /**
     * Función para limpiar el formulario
     */
    public void limpForm()
    {
        this.objeProcSoli = new ProcesoSolicitudes();
        this.guardar = true;        
    }
    
    /**
     * Función para guardar
     */
    public void guar(int a)
    {
        log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
            {
            Solicitudes soli = new Solicitudes();
            soli.setCodiSoli(a);
            this.objeProcSoli.setCodiSoli(soli);
            this.objeProcSoli.setEstaProcSoli(true);
            this.objeProcSoli.setFechProcSoli(new Date());
            FCDEProcSoli.create(this.objeProcSoli);
            this.listProcSoli.add(this.objeProcSoli);
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            log.info("Se han guardado correctamento los datos");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error("Ocurrio un error al momento de guardar");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para modificar un registro
     */
    public void modi()
    {
        log.debug("Se intenda modificar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listProcSoli.remove(this.objeProcSoli); //Limpia el objeto viejo
            FCDEProcSoli.edit(this.objeProcSoli);
            this.listProcSoli.add(this.objeProcSoli); //Agrega el objeto modificado
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
    
    /**
     * Función para dar de baja un registro
     */
    public void elim()
    {
        log.debug("Se esta intentado eliminar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeProcSoli.setEstaProcSoli(false);
            this.listProcSoli.remove(this.objeProcSoli); //Limpia el objeto viejo
            FCDEProcSoli.edit(this.objeProcSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Los datos se han eliminado correctamente");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para consultar todos los registros de la tabla
     */
    public void consTodo()
    {
        log.debug("Se esta intentando consultar todo");
        try
        {
            this.listProcSoli = FCDEProcSoli.findTodo();
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
    
    /**
     * Función para consultar un registro en específico
     */
    public void cons()
    {
        log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeProcSoli = FCDEProcSoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeProcSoli.getCodiProcSoli()) + "')");
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
