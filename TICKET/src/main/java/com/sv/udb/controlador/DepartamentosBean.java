/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.DepartamentosFacadeLocal;
import com.sv.udb.modelo.Departamentos;
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
 * @author
 */
@Named(value = "departamentosBean")
@RequestScoped
public class DepartamentosBean implements Serializable {
    @EJB
    private DepartamentosFacadeLocal FCDEDepa;
    private Departamentos objeDepa;
    private List<Departamentos> listDepa;
    private static Date fecha;
    private boolean guardar;

    public Departamentos getObjeDepa() {
        return objeDepa;
    }

    public void setObjeDepa(Departamentos objeDepa) {
        this.objeDepa = objeDepa;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public List<Departamentos> getListDepa() {
        return listDepa;
    }

    public void setListDepa(List<Departamentos> listDepa) {
        this.listDepa = listDepa;
    }

    /**
     * Creates a new instance of DepartamentosBean
     */
    public DepartamentosBean() {
    }

    @PostConstruct
    public void init() {
        this.limpForm();
        this.consTodo();
    }

    public void limpForm() {
        this.objeDepa = new Departamentos();
        this.guardar = true;
    }
    
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try {
            objeDepa.setFechIngrDepa(new Date());
            objeDepa.setEstaDepa(true);
            FCDEDepa.create(this.objeDepa);
            this.listDepa.add(this.objeDepa);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
        public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
       
        try
        {
          if(this.objeDepa.getCodiDepa()== null){
              ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
          }else{
            objeDepa.setFechIngrDepa(fecha);
            this.objeDepa.setEstaDepa(true);
            FCDEDepa.edit(this.objeDepa);
            limpForm();
            consTodo();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
          }
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        
    }
        
        public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            if (this.objeDepa.getCodiDepa() == null) {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No ha seleccionado una solicitud')");
            }
            else{
            this.listDepa.remove(this.objeDepa); //Limpia el objeto viejo
            objeDepa.setFechIngrDepa(fecha);
            this.objeDepa.setEstaDepa(false);
            FCDEDepa.edit(this.objeDepa);
            limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
            }
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    public void consTodo() {
        try {
            this.listDepa = FCDEDepa.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiDepaPara"));
        try
        {
            this.objeDepa = FCDEDepa.find(codi);
            this.guardar = false;
            fecha = objeDepa.getFechIngrDepa();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeDepa.getNombDepa()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
