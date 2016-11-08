/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EquiposFacadeLocal;
import com.sv.udb.modelo.Equipos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alexander
 */
@Named(value = "equiposBean")
@ViewScoped
public class EquiposBean implements Serializable{

    @EJB
    private EquiposFacadeLocal FCDEEqui;
    private Equipos objeEqui;

    private List<Equipos> listEqui;

    public List<Equipos> getListEqui() {
        return listEqui;
    }
    
    public Equipos getObjeEqui() {
        return objeEqui;
    }

    public void setObjeEqui(Equipos objeEqui) {
        this.objeEqui = objeEqui;
    }
    
    /**
     * Creates a new instance of EquiposBean
     */
    public EquiposBean() {
    }
    @PostConstruct
    public void init()
    {
        this.consTodo();
    }
    
    public void consTodo()
    {
        try
        {
            this.listEqui = FCDEEqui.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    
    public Equipos consEqui(int codi){
        try{
            this.objeEqui = FCDEEqui.find(codi);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return this.objeEqui;
    }
}
