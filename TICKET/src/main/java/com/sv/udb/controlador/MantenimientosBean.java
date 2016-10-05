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
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alexander
 */
@Named(value = "mantenimientosBean")
@RequestScoped
public class MantenimientosBean implements Serializable {

    @EJB
    private MantenimientosFacadeLocal FCDEMant;
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
    
    /**
     * Creates a new instance of MantenimientosBean
     */
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
            this.listMant = FCDEMant.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {            
            objeMant.setEstaMantPrev(true);
            FCDEMant.create(this.objeMant);
            this.listMant.add(this.objeMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }

    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeMant.getCodiMant()== null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado un mantenimiento')");
            } else {
                this.listMant.remove(this.objeMant); //Limpia el objeto viejo
                FCDEMant.edit(this.objeMant);
                this.listMant.add(this.objeMant); //Agrega el objeto modificado
                limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }

    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeMant.getCodiMant()== null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado un mantenimiento')");
            } else {
                FCDEMant.remove(this.objeMant);
                this.listMant.remove(this.objeMant);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiMantPara"));
        try {
            this.objeMant = FCDEMant.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
