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
 * Esta clase se encuentran los metodos para el manejo de los datos (CRUD) del objeto objeEvalReso
 * @author Alexander
 * @version 1.2
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
    
    /**
     * Función para obtener  el objeto objeEvalReso
     * @return EvaluacionResoluciones objeEvalReso
     */
    public EvaluacionResoluciones getObjeEvalReso() {
        return objeEvalReso;
    }

    /**
     * Función para definir el objeto objeEvalReso
     * @param objeEvalReso
     */
    public void setObjeEvalReso(EvaluacionResoluciones objeEvalReso) {
        this.objeEvalReso = objeEvalReso;
    }

    /**
     * Función que retorna la lista de objetos de EvaluacionResoluciones
     * @return List listEvalReso
     */
    public List<EvaluacionResoluciones> getListEvalReso() {
        return listEvalReso;
    }

    /**
     * Función para definir la lista de objetos de EvaluacionResoluciones
     * @param listEvalReso
     */
    public void setListEvalReso(List<EvaluacionResoluciones> listEvalReso) {
        this.listEvalReso = listEvalReso;
    }

    /**
     * Función que retorna el valor de la variable guardar para saber si se está guardando o no actualmente
     * @return Boolean guardar
     */
    public boolean isGuardar() {
        return guardar;
    }

    /**
     * Función para definir si se esta guardando o no
     * @param guardar
     */
    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    /**
     * Creates a new instance of EvaluacionResolucionesBean
     */
    public EvaluacionResolucionesBean() {
    }

    /**
     * Función que se ejecuta después de construir la clase
     */
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
        log.debug("Se ha inicializado el bean");
    }

    /**
     * Función para limpiar el formulario
     */
    public void limpForm() {
        this.objeEvalReso = new EvaluacionResoluciones();
        this.guardar = true;
    }

    /**
     * Función para consultar todos los registros de la tabla
     */
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

    /**
     * Función para guardar
     */
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

    /**
     * Función para modificar un registro
     */
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

    /**
     * Función para dar de baja un registro
     */
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

    /**
     * Función para consultar un registro en específico
     */
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
