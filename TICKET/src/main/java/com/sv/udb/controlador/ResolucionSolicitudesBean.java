/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ResolucionSolicitudesFacadeLocal;
import com.sv.udb.modelo.ResolucionSolicitudes;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author oscar
 */
@Named(value = "resolucionSolicitudesBean")
@RequestScoped
public class ResolucionSolicitudesBean {

    @EJB
    private ResolucionSolicitudesFacadeLocal FCDEResoSoli;
    private ResolucionSolicitudes objeResoSoli;
    private List<ResolucionSolicitudes> listResoSoli;
    private boolean guardar;
    private String estado = "none";

    /**
     * Creates a new instance of ResolucionSolicitudes
     */
    public ResolucionSolicitudesBean() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ResolucionSolicitudes getObjeResoSoli() {
        return objeResoSoli;
    }

    public void setObjeResoSoli(ResolucionSolicitudes objeResoSoli) {
        this.objeResoSoli = objeResoSoli;
    }

    public List<ResolucionSolicitudes> getListResoSoli() {
        return listResoSoli;
    }

    public void setListResoSoli(List<ResolucionSolicitudes> listResoSoli) {
        this.listResoSoli = listResoSoli;
    }

    public boolean isGuardar() {
        return guardar;
    }

    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeResoSoli = new ResolucionSolicitudes();
        this.guardar = true;
    }

    public void guar() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            FCDEResoSoli.create(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }

    public void modi() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo

            FCDEResoSoli.edit(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            this.limpForm();
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }

    public void elim() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            if (this.objeResoSoli.getCodiResoSoli() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            } else {
                FCDEResoSoli.remove(this.objeResoSoli);
                this.listResoSoli.remove(this.objeResoSoli);
                this.limpForm();
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }

    public void consTodo() {
        try {
            this.listResoSoli = FCDEResoSoli.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cons() {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiResoSoliPara"));
        try {
            this.objeResoSoli = FCDEResoSoli.find(codi);
            this.guardar = false;
            this.estado = "block";
        } catch (Exception ex) {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }

}
