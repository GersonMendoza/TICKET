/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.DepartamentosFacadeLocal;
import com.sv.udb.modelo.Departamentos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alexander
 */
@Named(value = "departamentosBean")
@ViewScoped
public class DepartamentosBean implements Serializable {

    @EJB
    private DepartamentosFacadeLocal FCDEDepa;
    private Departamentos objeDepa;
    private List<Departamentos> listDepa;
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

    public void consTodo() {
        try {
            this.listDepa = FCDEDepa.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
