/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuarioFacadeLocal;
import com.sv.udb.modelo.Usuario;
import com.sv.udb.utils.LOG4J;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Carlos
 */
@Named(value = "usuarioBean")
@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable{
    //Campos de la clase
    @EJB
    private UsuarioFacadeLocal FCDEUsuario;
   
    private List<Usuario> listUsua;
    private Usuario objeUsua;
    private boolean guardar;
    private LOG4J log;
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public List<Usuario> getListUsua() {
        return listUsua;
    }

    public void setListUsua(List<Usuario> listUsua) {
        this.listUsua = listUsua;
    }

    public Usuario getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuario objeUsua) {
        this.objeUsua = objeUsua;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public UsuarioBean() {
    }
    
    /**
    * Método que se ejecuta después de haber construido la clase e inicializa las variables
    */
    @PostConstruct
    public void init()
    {
        this.listUsua = FCDEUsuario.findAll();
        //log = new LOG4J();
        //log.debug("Se inicializa el modelo de Usuarios");
    }
    
    /**
     * Método que consulta la información de la base de datos
     */
    public void consTodo()
    {
        try
        {
            this.listUsua = FCDEUsuario.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
}
