/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.modelo.EquiposSolicitudes;
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
public class EquiposSolicitudesFacade extends AbstractFacade<EquiposSolicitudes> implements EquiposSolicitudesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquiposSolicitudesFacade() {
        super(EquiposSolicitudes.class);
    }
    
    @Override
    public Long cantEqui(int codi) {
        Query q = getEntityManager().createQuery("SELECT COUNT(u) FROM EquiposSolicitudes u WHERE u.codiSoli.codiSoli = :codiSoli", EquiposSolicitudes.class);
        q.setParameter("codiSoli", codi);
        long resu = (long)q.getSingleResult();
        return resu;
    }
    
    @Override
    public List<Equipos> findTodo(int codi) {
        Query q = getEntityManager().createQuery("SELECT e FROM Equipos e INNER JOIN EquiposSolicitudes es ON es.codiEqui.codiEqui = e.codiEqui WHERE es.codiSoli.codiSoli = :codiSoli", EquiposSolicitudes.class);
        q.setParameter("codiSoli", codi);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public String findDesc(int codiEqui, int codiSoli) {
        Query q = getEntityManager().createQuery("SELECT u.descEquiSoli FROM EquiposSolicitudes u WHERE u.codiSoli.codiSoli = :codiSoli AND u.codiEqui.codiEqui = :codiEqui", EquiposSolicitudes.class);
        q.setParameter("codiEqui", codiEqui);
        q.setParameter("codiSoli", codiSoli);
        String desc = (String)q.getSingleResult();
        return desc;
    }
}
