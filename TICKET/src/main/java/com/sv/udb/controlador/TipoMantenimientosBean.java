/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.TipoMantenimientosFacadeLocal;
import com.sv.udb.modelo.TipoMantenimientos;
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
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alexander
 */
@Named(value = "tipoMantenimientosBean")
@RequestScoped
public class TipoMantenimientosBean implements Serializable {

    @EJB
    private TipoMantenimientosFacadeLocal FCDETipoMant;
    private TipoMantenimientos objeTipoMant;
    private List<TipoMantenimientos> listTipoMant;
    private boolean guardar;
    private String estado = "none";

    public TipoMantenimientos getObjeTipoMant() {
        return objeTipoMant;
    }

    public void setObjeTipoMant(TipoMantenimientos objeTipoMant) {
        this.objeTipoMant = objeTipoMant;
    }

    public List<TipoMantenimientos> getListTipoMant() {
        return listTipoMant;
    }

    public void setListTipoMant(List<TipoMantenimientos> listTipoMant) {
        this.listTipoMant = listTipoMant;
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
    
    
    /**
     * Creates a new instance of TipoMantenimientosBean
     */
    public TipoMantenimientosBean() {
    }
    
    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }
    
    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            Calendar Calendario = new GregorianCalendar().getInstance();
            Date Fecha = Calendario.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String FechaAlta = formatoDeFecha.format(Fecha);
            try {
                objeTipoMant.setFechIngrTipoMant(formatoDeFecha.parse(FechaAlta));
                objeTipoMant.setFechBajaTipoMant(null);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            FCDETipoMant.create(this.objeTipoMant);
            this.listTipoMant.add(this.objeTipoMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            Calendar Calendario = new GregorianCalendar().getInstance();
            Date Fecha = Calendario.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String FechaAlta = formatoDeFecha.format(Fecha);
            try {
                objeTipoMant.setFechBajaTipoMant(formatoDeFecha.parse(FechaAlta));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            FCDETipoMant.create(this.objeTipoMant);
            this.listTipoMant.add(this.objeTipoMant);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeTipoMant.getCodiTipoMant() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                FCDETipoMant.remove(this.objeTipoMant);
                this.listTipoMant.remove(this.objeTipoMant);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void limpForm() {
        this.objeTipoMant = new TipoMantenimientos();
        this.guardar = true;
        Calendar Calendario = new GregorianCalendar().getInstance();
            Date Fecha = Calendario.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String FechaAlta = formatoDeFecha.format(Fecha);
            try {
                objeTipoMant.setFechIngrTipoMant(formatoDeFecha.parse(FechaAlta));
                objeTipoMant.setFechBajaTipoMant(formatoDeFecha.parse(FechaAlta));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
    }
    
    public void consTodo() {
        try {
            this.listTipoMant = FCDETipoMant.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiTipoMantPara"));
        try {
            this.objeTipoMant = FCDETipoMant.find(codi);
            this.guardar = false;
            this.estado = "block";
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
