/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.DepartamentosFacadeLocal;
import com.sv.udb.modelo.Departamentos;
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
@Named(value = "departamentosBean")
@ViewScoped
public class DepartamentosBean implements Serializable{
    @EJB
    private DepartamentosFacadeLocal FCDEDepa;    
    private Departamentos objeDepa;
    private List<Departamentos> listDepa;
    private boolean guardar;
    private Logs<DepartamentosBean> lgs = new Logs<DepartamentosBean>(DepartamentosBean.class) {
    };
    private Logger log = lgs.getLog();

    public Departamentos getObjeDepa() {
        return objeDepa;
    }

    public void setObjeDepa(Departamentos objeDepa) {
        this.objeDepa = objeDepa;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Departamentos> getListDepa() {
        return listDepa;
    }
    
    /**
     * Creates a new instance of DepartamentosBean
     */
    
    public DepartamentosBean() {
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
        this.objeDepa = new Departamentos();
        this.guardar = true;
    }
    
    public void guar()
    {
        log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeDepa.setEstaDepa(true);
            this.objeDepa.setFechIngrDepa(new Date());
            FCDEDepa.create(this.objeDepa);
            this.listDepa.add(this.objeDepa);
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
            this.listDepa.remove(this.objeDepa); //Limpia el objeto viejo
            FCDEDepa.edit(this.objeDepa);
            this.listDepa.add(this.objeDepa); //Agrega el objeto modificado
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
            this.objeDepa.setFechBajaDepa(new Date());
            this.objeDepa.setEstaDepa(false);
            this.listDepa.remove(this.objeDepa); //Limpia el objeto viejo
            FCDEDepa.edit(this.objeDepa);
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
            this.listDepa = FCDEDepa.findTodo();
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
            this.objeDepa = FCDEDepa.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDepa.getNombDepa()) + "')");
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
