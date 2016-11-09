/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Mantenimientos;
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
public class MantenimientosFacade extends AbstractFacade<Mantenimientos> implements MantenimientosFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MantenimientosFacade() {
        super(Mantenimientos.class);
    }
    
    @Override
    public List<Mantenimientos> findTodo() {
        Query q = getEntityManager().createQuery("SELECT u FROM Mantenimientos u WHERE u.estaMantPrev ="+true, Mantenimientos.class);
        List resu = q.getResultList();
        return resu;
    }
    
}
