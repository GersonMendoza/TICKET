/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.EquiposFacadeLocal;
import com.sv.udb.modelo.Equipos;
import java.io.Serializable;
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
