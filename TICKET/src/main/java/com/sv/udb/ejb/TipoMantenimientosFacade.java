/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoMantenimientos;
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
public class TipoMantenimientosFacade extends AbstractFacade<TipoMantenimientos> implements TipoMantenimientosFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMantenimientosFacade() {
        super(TipoMantenimientos.class);
    }
    
    @Override
    public List<TipoMantenimientos> findTodo() {
    Query q = getEntityManager().createQuery("SELECT u FROM TipoMantenimientos u WHERE u.estaTipoMant ="+true, TipoMantenimientos.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public String findTipoMantSoli(int codi) {
        Query q = getEntityManager().createQuery("SELECT tm.nombTipoMant FROM TipoMantenimientos tm INNER JOIN Mantenimientos m ON m.codiTipoMant.codiTipoMant = tm.codiTipoMant WHERE m.codiMant = :codiMant", TipoMantenimientos.class);
        q.setParameter("codiMant", codi);
        String nombTipo = (String)q.getSingleResult();
        return nombTipo;
    }
}
