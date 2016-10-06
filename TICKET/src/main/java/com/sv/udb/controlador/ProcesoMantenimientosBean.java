/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ProcesoMantenimientosFacadeLocal;
import com.sv.udb.modelo.ProcesoMantenimientos;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author iKronyck
 */
@Named(value = "procesoMantenimientosBean")
@RequestScoped
public class ProcesoMantenimientosBean implements Serializable{

    @EJB
    private ProcesoMantenimientosFacadeLocal FCDEprocesoMantenimientos;

    private ProcesoMantenimientos objeProcMant;
    private List<ProcesoMantenimientos> listProcMant;
    private boolean guardar;

    public ProcesoMantenimientos getObjeProcMant() {
        return objeProcMant;
    }

    public void setObjeProcMant(ProcesoMantenimientos objeProcMant) {
        this.objeProcMant = objeProcMant;
    }

    public List<ProcesoMantenimientos> getListProcMant() {
        return listProcMant;
    }

    public void setListProcMant(List<ProcesoMantenimientos> listProcMant) {
        this.listProcMant = listProcMant;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public ProcesoMantenimientosBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeProcMant = new ProcesoMantenimientos();
        this.guardar = true;
    }

    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            Calendar Calendario = new GregorianCalendar().getInstance();
            Date Fecha = Calendario.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String FechaAlta = formatoDeFecha.format(Fecha);
            try {
                objeProcMant.setFechProcMant(formatoDeFecha.parse(FechaAlta));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            objeProcMant.setEstaProcMant(true);
            FCDEprocesoMantenimientos.create(this.objeProcMant);
            this.listProcMant.add(this.objeProcMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }

    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeProcMant.getCodiProcMant() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                
                this.listProcMant.remove(this.objeProcMant); //Limpia el objeto viejo
                FCDEprocesoMantenimientos.edit(this.objeProcMant);
                this.listProcMant.add(this.objeProcMant); //Agrega el objeto modificado
                this.consTodo();
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
            if (this.objeProcMant.getCodiProcMant() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                FCDEprocesoMantenimientos.remove(this.objeProcMant);
                this.listProcMant.remove(this.objeProcMant);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void consTodo() {
        try {
            this.listProcMant = FCDEprocesoMantenimientos.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiProcMantPara"));
        try {
            this.objeProcMant = FCDEprocesoMantenimientos.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
