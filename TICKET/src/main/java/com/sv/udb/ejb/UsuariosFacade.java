/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    @Override
    public Usuarios findByAcceAndCont(Object acce, Object cont) {
        Query q = getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.acceUsua = :acceUsua AND u.contUsua = :contUsua AND u.estaUsua = :estaUsua", Usuarios.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("contUsua", cont);
        q.setParameter("estaUsua", 1);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Usuarios)resu.get(0);
    }
}
