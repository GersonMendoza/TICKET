/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.SolicitudesFacadeLocal;
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
@Named(value = "solicitudesBean")
@ViewScoped
public class SolicitudesBean implements Serializable{
    @EJB
    private SolicitudesFacadeLocal FCDESoli;    
    private Solicitudes objeSoli;
    private List<Solicitudes> listSoli;
    private List<Solicitudes> listSoliEnca;
    private List<Solicitudes> listSoliTecn;
    static int codiSoli;
    private boolean guardar;
    private Logs<SolicitudesBean> lgs = new Logs<SolicitudesBean>(SolicitudesBean.class) {
    };
    private Logger log = lgs.getLog();

    public Solicitudes getObjeSoli() {
        return objeSoli;
    }

    public void setObjeSoli(Solicitudes objeSoli) {
        this.objeSoli = objeSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Solicitudes> getListSoli() {
        return listSoli;
    }

    public List<Solicitudes> getListSoliEnca() {
        return listSoliEnca;
    }

    public List<Solicitudes> getListSoliTecn() {
        return listSoliTecn;
    }
    
    
    
    
    /**
     * Creates a new instance of SolicitudesBean
     */
    
    public SolicitudesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        this.consEncargado();
        this.consTecnico();
        log.debug("Se ha inicializado el bean");
    }
    
    public void limpForm()
    {
        this.objeSoli = new Solicitudes();
        this.guardar = true;        
    }
    
    public void guar()
    {
        log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            LoginBean login = new LoginBean();
            this.objeSoli.setCodiUsua(login.codiUsua);
            this.objeSoli.setEstaSoli(1);
            this.objeSoli.setFechHoraSoli(new Date());
            FCDESoli.create(this.objeSoli);
            this.listSoli.add(this.objeSoli);
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
    
    
    public void modi()
    {
        log.debug("Se intenda modificar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            FCDESoli.edit(this.objeSoli);
            this.listSoli.add(this.objeSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Los datos de han modificado correctamente en el bean");
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
            this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            FCDESoli.remove(this.objeSoli);
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
            this.listSoli = FCDESoli.findTodo();
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
    
    public void consEncargado()
    {
        log.debug("Se intenta consultar Encargado");
        try
        {
            this.listSoliEnca = FCDESoli.findEncargado();
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar");
        }
        finally
        {
            
        }
    }
    
    public void consTecnico()
    {
        log.debug("Se intenta consultar Técnico");
        try
        {
            this.listSoliTecn = FCDESoli.findTecnico();
            log.info("La consulta se hizo correctamente");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar");
        }
        finally
        {
            
        }
    }
    
    public void modiAsigEnca()
    {
        log.debug("Se intenda modificar las asignaciones encargado");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeSoli.setEstaSoli(2);
            this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            FCDESoli.edit(this.objeSoli);
            this.consEncargado();
            this.limpForm();
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
    
    public void cons()
    {
        log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeSoli = FCDESoli.find(codi);
            codiSoli = objeSoli.getCodiSoli();
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeSoli.getCodiSoli()) + "')");
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
