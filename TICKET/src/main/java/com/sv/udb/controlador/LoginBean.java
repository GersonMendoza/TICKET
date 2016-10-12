/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.utils.Notificacion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 5074501358281220977L;

    @EJB
    private UsuariosFacadeLocal FCDEUsua;
    
    @Inject
    private GlobalAppBean globalAppBean; //Bean de aplicación
    
    private Usuarios objeUsua;
    private boolean loge;
    private String usua;
    private String cont;
    private String imagPerf;
    private List<Notificacion> listNoti; //Lista de Notificaciones

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init()
    {
        
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public boolean isLoge() {
        return loge;
    }

    public String getUsua() {
        return usua;
    }

    public void setUsua(String usua) {
        this.usua = usua;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getImagPerf() {
        return imagPerf;
    }

    public List<Notificacion> getListNoti() {
        return listNoti;
    }
    
    public void creaSess()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        try
        {
            this.loge = false;
            this.objeUsua = FCDEUsua.findByAcceAndCont(this.usua, this.cont);
            if(this.objeUsua != null)
            {
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Bienvenido)"); //No se muestra porque redirecciona
                this.loge = true;
                //Cargar una imagen de usuario (Puede ser de una BD)
                this.imagPerf = "images/userDemo.png";
                //Llenar lista de notificaciones.... puede salir de la DB
                this.listNoti = new ArrayList<>();
                this.listNoti.add(new Notificacion("Notificación 1", false));
                this.listNoti.add(new Notificacion("Notificación 2", false));
                this.listNoti.add(new Notificacion("Notificación 3", false));
                this.listNoti.add(new Notificacion("Notificación 4", false));
                this.listNoti.add(new Notificacion("Notificación 5", false));
                this.listNoti.add(new Notificacion("Notificación 6", false));
                this.listNoti.add(new Notificacion("Notificación 7", false));
                this.listNoti.add(new Notificacion("Notificación 8", false));
                //Redireccionar
                facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("index.xhtml"));
            }
            else
            {
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Ingreso incorrecto')");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al logear')");
        }
        finally
        {
            
        }
    }
    
    public void cerrSess()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        try
        {
            facsCtxt.getExternalContext().invalidateSession();
            facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("login.xhtml")); 
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al finalizar la sesión')");
        }
        finally
        {
            
        }       
    }

}
