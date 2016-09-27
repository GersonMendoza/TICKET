/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.SolicitudesFacadeLocal;
import com.sv.udb.modelo.Solicitudes;
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
@Named(value = "solicitudesBean")
@RequestScoped
public class SolicitudesBean implements Serializable {

    @EJB
    private SolicitudesFacadeLocal FCDESoli;
    private Solicitudes objeSoli;
    private List<Solicitudes> listSoli;
    private boolean guardar;

    public Solicitudes getObjeSoli() {
        return objeSoli;
    }

    public void setObjeSoli(Solicitudes objeSoli) {
        this.objeSoli = objeSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Solicitudes> getListSoli() {
        return listSoli;
    }

    /**
     * Creates a new instance of SolicitudesBean
     */
    public SolicitudesBean() {
    }

    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeSoli = new Solicitudes();
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
                objeSoli.setFechHoraSoli(formatoDeFecha.parse(FechaAlta));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            objeSoli.setEstaSoli(1);
            FCDESoli.create(this.objeSoli);
            this.listSoli.add(this.objeSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }

    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            FCDESoli.edit(this.objeSoli);
            this.listSoli.add(this.objeSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }

    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            FCDESoli.remove(this.objeSoli);
            this.listSoli.remove(this.objeSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void consTodo() {
        try {
            this.listSoli = FCDESoli.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiSoliPara"));
        try {
            this.objeSoli = FCDESoli.find(codi);
            this.guardar = false;
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
