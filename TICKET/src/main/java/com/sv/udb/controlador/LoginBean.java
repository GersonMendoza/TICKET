/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.utils.Logs;
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
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 * Esta clase se encuentran los metodos para el manejo del inicio de sesión
 * @author Gerson
 * @version 1.2
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
     * Variable que almacena el ID del usuario logeado
     */
    public static int codiUsua;
    private Logs<LoginBean> lgs = new Logs<LoginBean>(LoginBean.class) {
    };
    private Logger log = lgs.getLog();

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    /**
     * Función que se ejecuta después de construir la clase
     */
    @PostConstruct
    public void init()
    {
    }

    /**
     * Función para obtener  el objeto objeUsua
     * @return Usuarios objeUsua
     */
    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    /**
     * Función para definir el objeto objeUsua
     * @param objeUsua
     */
    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    /**
     * Función que retorna el valor de la variable loge para saber si se está logeado o no actualmente
     * @return Boolean loge
     */
    public boolean isLoge() {
        return loge;
    }

    /**
     * Función para obtener el nombre de usuario
     * @return String usua
     */
    public String getUsua() {
        return usua;
    }

    /**
     * Función para definir el nombre de usuario
     * @param usua
     */
    public void setUsua(String usua) {
        this.usua = usua;
    }

    /**
     * Función para obtener la contraseña de usuario
     * @return String cont
     */
    public String getCont() {
        return cont;
    }

    /**
     * Función para definir la contraseña de usuario
     * @param cont
     */
    public void setCont(String cont) {
        this.cont = cont;
    }

    /**
     * Función para obtener la imagen de perfil
     * @return String imagPerf
     */
    public String getImagPerf() {
        return imagPerf;
    }

    /**
     * Función que devuelve un listado de las notificaciones
     * @return List listNoti
     */
    public List<Notificacion> getListNoti() {
        return listNoti;
    }
    
    /**
     * Función que verifica los datos del login y crea la sesión
     */
    public void creaSess()
    {
        log.debug("Se intenta crear inicializar una nueva sessión");
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
                codiUsua = objeUsua.getCodiUsua();
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
                log.info("una sesión fue correctamente inicializada");
                //Redireccionar
                facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("index.xhtml"));
            }
            else
            {
                log.warn("Se intento iniciar sesión con credenciales incorrectas");
                ctx.execute("setMessage('MESS_WARN', 'Atención', 'Ingreso incorrecto')");
            }
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error cuando se intento crear una sesión");
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al logear')");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para cerrar sesión
     */
    public void cerrSess()
    {
        log.debug("se intenta cerrar una sesión");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        try
        {
            facsCtxt.getExternalContext().invalidateSession();
            facsCtxt.getExternalContext().redirect(globalAppBean.getUrl("login.xhtml")); 
            log.info("Una sesión fue correctamente cerrada");
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de cerrar la sesión");
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al finalizar la sesión')");
        }
        finally
        {
            
        }       
    }

}
