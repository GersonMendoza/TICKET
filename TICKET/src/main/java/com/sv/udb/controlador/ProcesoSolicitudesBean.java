/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ProcesoSolicitudesFacadeLocal;
import com.sv.udb.modelo.ProcesoSolicitudes;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author iKronyck
 */
@Named(value = "procesoSolicitudesBean")
@RequestScoped
public class ProcesoSolicitudesBean implements Serializable{

    @EJB
    private ProcesoSolicitudesFacadeLocal FCDEprocesoSolicitudes;
    
    private ProcesoSolicitudes objeProcSoli;
    private List<ProcesoSolicitudes> listProcSoli;
    private boolean guardar;

    public ProcesoSolicitudes getObjeProcSoli() {
        return objeProcSoli;
    }

    public void setObjeProcSoli(ProcesoSolicitudes objeProcSoli) {
        this.objeProcSoli = objeProcSoli;
    }

    public List<ProcesoSolicitudes> getListProcSoli() {
        return listProcSoli;
    }

    public void setListProcSoli(List<ProcesoSolicitudes> listProcSoli) {
        this.listProcSoli = listProcSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
    public ProcesoSolicitudesBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeProcSoli = new ProcesoSolicitudes();
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
                objeProcSoli.setFechProcSoli(formatoDeFecha.parse(FechaAlta));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            objeProcSoli.setEstaProcSoli(true);
            FCDEprocesoSolicitudes.create(this.objeProcSoli);
            this.listProcSoli.add(this.objeProcSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }

    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeProcSoli.getCodiProcSoli() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                
                this.listProcSoli.remove(this.objeProcSoli); //Limpia el objeto viejo
                FCDEprocesoSolicitudes.edit(this.objeProcSoli);
                this.listProcSoli.add(this.objeProcSoli); //Agrega el objeto modificado
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
            if (this.objeProcSoli.getCodiProcSoli() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                FCDEprocesoSolicitudes.remove(this.objeProcSoli);
                this.listProcSoli.remove(this.objeProcSoli);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void consTodo() {
        try {
            this.listProcSoli = FCDEprocesoSolicitudes.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiSoliPara"));
        try {
            this.objeProcSoli = FCDEprocesoSolicitudes.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
