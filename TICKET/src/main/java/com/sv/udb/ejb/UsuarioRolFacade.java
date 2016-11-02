/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.UsuarioRol;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adonay
 */
@Stateless
public class UsuarioRolFacade extends AbstractFacade<UsuarioRol> implements UsuarioRolFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRolFacade() {
        super(UsuarioRol.class);
    }
    
    @Override
    public UsuarioRol findByUsuaAndRole(Object usua, Object codiRole) {
        Query q = getEntityManager().createQuery("SELECT u FROM UsuarioRol u WHERE u.codiUsua = :codiUsua AND u.codiRole = :codiRole", UsuarioRol.class);        
        q.setParameter("codiUsua", usua);
        q.setParameter("codiRole", codiRole);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (UsuarioRol)resu.get(0);
    }
}
