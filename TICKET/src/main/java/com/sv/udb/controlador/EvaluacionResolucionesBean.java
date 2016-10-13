/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EvaluacionResolucionesFacadeLocal;
import com.sv.udb.modelo.EvaluacionResoluciones;
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
 * @author Alexander
 */
@Named(value = "evaluacionResolucionesBean")
@ViewScoped
public class EvaluacionResolucionesBean implements Serializable {

    @EJB
    private EvaluacionResolucionesFacadeLocal FCDEEvalReso;
    private EvaluacionResoluciones objeEvalReso;
    private List<EvaluacionResoluciones> listEvalReso;
    private boolean guardar;
    
    public EvaluacionResoluciones getObjeEvalReso() {
        return objeEvalReso;
    }

    public void setObjeEvalReso(EvaluacionResoluciones objeEvalReso) {
        this.objeEvalReso = objeEvalReso;
    }

    public List<EvaluacionResoluciones> getListEvalReso() {
        return listEvalReso;
    }

    public void setListEvalReso(List<EvaluacionResoluciones> listEvalReso) {
        this.listEvalReso = listEvalReso;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    /**
     * Creates a new instance of EvaluacionResolucionesBean
     */
    public EvaluacionResolucionesBean() {
    }

    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeEvalReso = new EvaluacionResoluciones();
        this.guardar = true;
    }

    public void consTodo() {
        try {
            this.listEvalReso = FCDEEvalReso.findTodo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeEvalReso.setEstaEvalReso(true);
            this.objeEvalReso.setFechEvalReso(new Date());
            FCDEEvalReso.create(this.objeEvalReso);
            this.listEvalReso.add(this.objeEvalReso);
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
            this.listEvalReso.remove(this.objeEvalReso); //Limpia el objeto viejo
            FCDEEvalReso.edit(this.objeEvalReso);
            this.listEvalReso.add(this.objeEvalReso); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }

    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeEvalReso.setEstaEvalReso(false);
            this.listEvalReso.remove(this.objeEvalReso); //Limpia el objeto viejo
            FCDEEvalReso.edit(this.objeEvalReso);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try {
            this.objeEvalReso = FCDEEvalReso.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
