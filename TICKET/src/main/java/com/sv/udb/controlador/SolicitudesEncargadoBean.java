/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;
import com.sv.udb.ejb.SolicitudesFacadeLocal;
import com.sv.udb.modelo.Solicitudes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "solicitudesEncargadoBean")
@ViewScoped
public class SolicitudesEncargadoBean implements Serializable{
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
    
    public SolicitudesEncargadoBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consEncargado();
    }
    
    public void limpForm()
    {
        this.objeSoli = new Solicitudes();
        this.guardar = true;        
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeSoli.setEstaSoli(2);
            this.listSoli.remove(this.objeSoli); //Limpia el objeto viejo
            FCDESoli.edit(this.objeSoli);
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void consEncargado()
    {
        try
        {
            this.listSoli = FCDESoli.findEncargado();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeSoli = FCDESoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeSoli.getCodiSoli()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
}
