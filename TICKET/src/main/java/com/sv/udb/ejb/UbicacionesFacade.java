/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Ubicaciones;
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
public class UbicacionesFacade extends AbstractFacade<Ubicaciones> implements UbicacionesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionesFacade() {
        super(Ubicaciones.class);
    }
    
    @Override
    public List<Ubicaciones> findUbicMantSoli(int codi) {
        Query q = getEntityManager().createQuery("SELECT u FROM Ubicaciones u INNER JOIN UbicacionesMantenimiento um ON um.codiUbic.codiUbic = u.codiUbic WHERE um.codiMant.codiMant = :codiMant", Ubicaciones.class);
        q.setParameter("codiMant", codi);
        List resu = q.getResultList();
        return resu;
    }
}
