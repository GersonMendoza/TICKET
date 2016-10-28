/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import com.sv.udb.utils.Logs;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander
 */
@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable{

    @EJB
    private UsuariosFacadeLocal FCDEUsua;    
    private Usuarios objeUsua;
    private List<Usuarios> listTecn;
    private Logs<UsuariosBean> lgs = new Logs<UsuariosBean>(UsuariosBean.class) {
    };
    private Logger log = lgs.getLog();

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public List<Usuarios> getListTecn() {
        return listTecn;
    }

    public void setListTecn(List<Usuarios> listTecn) {
        this.listTecn = listTecn;
    }
          
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }
    
    /**
     * Función para consultar un registro en específico
     * @param codi
     * @return String
     */
    public String cons(int codi)
    {
        log.debug("Se intenta consultar");
        try
        {
            this.objeUsua = FCDEUsua.find(codi);
            log.info("La consulta se hizo correctamente");            
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de consultar");
        }
        return objeUsua.getAcceUsua();
    }
    
    public List<Usuarios> consTecn()
    {
        log.debug("Se intenta consultar");
        try
        {
            this.listTecn = FCDEUsua.findTecn();
            log.info("La consulta se hizo correctamente");            
        }
        catch(Exception ex)
        {
            log.error("Ocurrio un error al momento de consultar");
        }
        return this.listTecn;
    }
}
