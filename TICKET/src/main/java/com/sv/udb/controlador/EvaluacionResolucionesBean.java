/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EvaluacionResolucionesFacadeLocal;
import com.sv.udb.modelo.EvaluacionResoluciones;
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
    private Logs<EvaluacionResolucionesBean> lgs = new Logs<EvaluacionResolucionesBean>(EvaluacionResolucionesBean.class) {
    };
    private Logger log = lgs.getLog();
    
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
        log.debug("Se ha inicializado el bean");
    }

    public void limpForm() {
        this.objeEvalReso = new EvaluacionResoluciones();
        this.guardar = true;
    }

    public void consTodo() {
        log.debug("Se esta intentando consultar todo");
        try {
            this.listEvalReso = FCDEEvalReso.findTodo();
            log.info("La consulta se hizo correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Ocurrio un error al momento de consultar todo");
        }
    }

    public void guar() {
        log.debug("Se intenta guardar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeEvalReso.setEstaEvalReso(true);
            this.objeEvalReso.setFechEvalReso(new Date());
            FCDEEvalReso.create(this.objeEvalReso);
            this.listEvalReso.add(this.objeEvalReso);
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
             log.info("Se han guardado correctamento los datos");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            log.error("Ocurrio un error al momento de guardar");
        }
    }

    public void modi() {
         log.debug("Se intenda modificar en el bean");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.listEvalReso.remove(this.objeEvalReso); //Limpia el objeto viejo
            FCDEEvalReso.edit(this.objeEvalReso);
            this.listEvalReso.add(this.objeEvalReso); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            log.info("Los datos se han modificado correctamente en el bean");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            log.error("ocurrio un error al momento de modificar");
        }
    }

    public void elim() {
        log.debug("Se esta intentado eliminar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.objeEvalReso.setEstaEvalReso(false);
            this.listEvalReso.remove(this.objeEvalReso); //Limpia el objeto viejo
            FCDEEvalReso.edit(this.objeEvalReso);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            log.info("Los datos se han eliminado correctamente");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
            log.error("Ocurrio un error al momento de eliminar");
        }
    }

    public void cons() {
         log.debug("Se intenta consultar");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try {
            this.objeEvalReso = FCDEEvalReso.find(codi);
            this.guardar = false;
            log.info("La consulta se hizo correctamente");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            log.error("Ocurrio un error al momento de consultar");
        }
    }
}
