/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.controlador.LoginBean;
import com.sv.udb.modelo.Solicitudes;
import com.sv.udb.utils.FilterManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author gersonfrancisco
 */
@Stateless
public class SolicitudesFacade extends AbstractFacade<Solicitudes> implements SolicitudesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudesFacade() {
        super(Solicitudes.class);
    }
    
    @Override
    public List<Solicitudes> findTodo() {
        LoginBean login = new LoginBean();
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.codiUsua ="+login.codiUsua, Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findEncargado() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.estaSoli = 1", Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
}
