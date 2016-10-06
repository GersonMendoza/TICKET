/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.CorrelativoMantenimientosFacadeLocal;
import com.sv.udb.modelo.CorrelativoMantenimientos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author joseph
 */
@Named(value = "correlativoMantenimientosBean")
@RequestScoped
public class CorrelativoMantenimientosBean implements Serializable{

    @EJB
    private CorrelativoMantenimientosFacadeLocal FCDECorrMant;
    private CorrelativoMantenimientos objeCorrMant;
    private List<CorrelativoMantenimientos> listCorrMant;
    private static Date fecha;
    private boolean guardar;

    public CorrelativoMantenimientosFacadeLocal getFCDECorrMant() {
        return FCDECorrMant;
    }

    public void setFCDECorrMant(CorrelativoMantenimientosFacadeLocal FCDECorrMant) {
        this.FCDECorrMant = FCDECorrMant;
    }

    public CorrelativoMantenimientos getObjeCorrMant() {
        return objeCorrMant;
    }

    public void setObjeCorrMant(CorrelativoMantenimientos objeCorrMant) {
        this.objeCorrMant = objeCorrMant;
    }

    public List<CorrelativoMantenimientos> getListCorrMant() {
        return listCorrMant;
    }

    public void setListCorrMant(List<CorrelativoMantenimientos> listCorrMant) {
        this.listCorrMant = listCorrMant;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        CorrelativoMantenimientosBean.fecha = fecha;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public CorrelativoMantenimientosBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeCorrMant = new CorrelativoMantenimientos();
        this.guardar = true;
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            objeCorrMant.setFechCorrMant(new Date());
            objeCorrMant.setEstaCorrMant(true);
            FCDECorrMant.create(this.objeCorrMant);
            this.listCorrMant.add(this.objeCorrMant);
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void consTodo() {
        try {
            this.listCorrMant = FCDECorrMant.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiCorrMantPara"));
        try {
            this.objeCorrMant = FCDECorrMant.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
    
}
