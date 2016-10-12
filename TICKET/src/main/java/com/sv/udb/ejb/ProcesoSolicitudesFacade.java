/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.ProcesoSolicitudes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gersonfrancisco
 */
@Stateless
public class ProcesoSolicitudesFacade extends AbstractFacade<ProcesoSolicitudes> implements ProcesoSolicitudesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoSolicitudesFacade() {
        super(ProcesoSolicitudes.class);
    }
    
}
