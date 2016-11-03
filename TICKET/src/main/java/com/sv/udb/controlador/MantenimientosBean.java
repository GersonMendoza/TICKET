/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.MantenimientosFacadeLocal;
import com.sv.udb.modelo.Mantenimientos;
import java.io.Serializable;
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
@Named(value = "mantenimientosBean")
@ViewScoped
public class MantenimientosBean implements Serializable{

    @EJB
    private MantenimientosFacadeLocal FCDEmant;
    
    private Mantenimientos objeMant;
    private List<Mantenimientos> listMant;
    private boolean guardar;

    public Mantenimientos getObjeMant() {
        return objeMant;
    }

    public void setObjeMant(Mantenimientos objeMant) {
        this.objeMant = objeMant;
    }

    public List<Mantenimientos> getListMant() {
        return listMant;
    }

    public void setListMant(List<Mantenimientos> listMant) {
        this.listMant = listMant;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public MantenimientosBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
     }


    public void limpForm() {
        this.objeMant = new Mantenimientos();
        this.guardar = true;
    }
    
    public void consTodo() {
        try {
            this.listMant = FCDEmant.findTodo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeMant.setEstaMantPrev(true);
            FCDEmant.create(this.objeMant);
            this.listMant.add(this.objeMant);
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.listMant.remove(this.objeMant); //Limpia el objeto viejo
            FCDEmant.edit(this.objeMant);
            this.listMant.add(this.objeMant); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            //log.info("Los datos se han modificado correctamente en el bean");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            //log.error("ocurrio un error al momento de modificar");
        }
    }
    
     public void elim() {
        //log.debug("Se esta intentado eliminar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeMant.setEstaMantPrev(false);
            this.listMant.remove(this.objeMant); //Limpia el objeto viejo
            FCDEmant.edit(this.objeMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            //log.info("Los datos se han eliminado correctamente");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
            //log.error("Ocurrio un error al momento de eliminar");
        }
    }

    /**
     * Función para consultar un registro en específico
     */
    public void cons() {
         //log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try {
            this.objeMant = FCDEmant.find(codi);
            this.guardar = false;
            //log.info("La consulta se hizo correctamente");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            //log.error("Ocurrio un error al momento de consultar");
        }
    }
}
