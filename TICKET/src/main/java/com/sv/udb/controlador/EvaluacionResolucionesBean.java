/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EvaluacionResolucionesFacadeLocal;
import com.sv.udb.modelo.EvaluacionResoluciones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author oscar
 */
@Named(value = "evaluacionResolucionesBean")
@RequestScoped
public class EvaluacionResolucionesBean {
    @EJB
    private EvaluacionResolucionesFacadeLocal FCDEEvaReso;
    private EvaluacionResoluciones objeEvaReso;
    private List<EvaluacionResoluciones> listEvaReso;
    private boolean guardar;
    private String estado = "none";
    
    

    /**
     * Creates a new instance of EvaluacionResolucionesBean
     */
    public EvaluacionResolucionesBean() {
    }

    public EvaluacionResoluciones getObjeEvaReso() {
        return objeEvaReso;
    }

    public void setObjeEvaReso(EvaluacionResoluciones objeEvaReso) {
        this.objeEvaReso = objeEvaReso;
    }

    public List<EvaluacionResoluciones> getListEvaReso() {
        return listEvaReso;
    }

    public void setListEvaReso(List<EvaluacionResoluciones> listEvaReso) {
        this.listEvaReso = listEvaReso;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm() {
        this.objeEvaReso = new EvaluacionResoluciones();
        this.guardar = true;
    }
    
    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            FCDEEvaReso.create(this.objeEvaReso);
            this.listEvaReso.add(this.objeEvaReso);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (EJBException ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar"+ex.toString()+" ')");
        }
    }
    
    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.listEvaReso.remove(this.objeEvaReso); //Limpia el objeto viejo

            FCDEEvaReso.edit(this.objeEvaReso);
            this.listEvaReso.add(this.objeEvaReso); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            this.limpForm();
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeEvaReso.getCodiResoSoli() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                FCDEEvaReso.remove(this.objeEvaReso);
                this.listEvaReso.remove(this.objeEvaReso);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public void consTodo() {
        try {
            this.listEvaReso = FCDEEvaReso.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiEvaResoPara"));
        try {
            this.objeEvaReso = FCDEEvaReso.find(codi);
            this.guardar = false;
            this.estado = "block";
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
    
}
