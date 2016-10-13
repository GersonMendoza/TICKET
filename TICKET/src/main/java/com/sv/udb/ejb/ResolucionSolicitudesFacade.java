/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.controlador.LoginBean;
import com.sv.udb.modelo.ResolucionSolicitudes;
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
public class ResolucionSolicitudesFacade extends AbstractFacade<ResolucionSolicitudes> implements ResolucionSolicitudesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResolucionSolicitudesFacade() {
        super(ResolucionSolicitudes.class);
    }
    
    @Override
    public List<ResolucionSolicitudes> findTodoUsua() {        
        Query q = getEntityManager().createQuery("SELECT rs FROM ResolucionSolicitudes rs INNER JOIN Solicitudes s ON s.codiSoli = rs.codiSoli.codiSoli WHERE rs.estaResoSoli = " + true + " AND s.codiUsua = " + LoginBean.codiUsua, ResolucionSolicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
}
