/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.controlador.LoginBean;
import com.sv.udb.modelo.EvaluacionResoluciones;
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
public class EvaluacionResolucionesFacade extends AbstractFacade<EvaluacionResoluciones> implements EvaluacionResolucionesFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluacionResolucionesFacade() {
        super(EvaluacionResoluciones.class);
    }

    @Override
    public List<EvaluacionResoluciones> findTodo() {
        Query q = getEntityManager().createQuery("SELECT er FROM EvaluacionResoluciones er WHERE er.estaEvalReso=" + true, EvaluacionResoluciones.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<EvaluacionResoluciones> findEvalUsua() {
        Query q = getEntityManager().createQuery("SELECT er FROM EvaluacionResoluciones er INNER JOIN ResolucionSolicitudes rs ON rs.codiResoSoli = er.codiResoSoli.codiResoSoli INNER JOIN Solicitudes s ON rs.codiSoli.codiSoli = s.codiSoli WHERE s.codiUsua = :codiUsua ORDER BY er.codiEvalReso DESC", EvaluacionResoluciones.class);
        LoginBean login = new LoginBean();
        q.setParameter("codiUsua", login.getObjeUsua().getCodiUsua());
        List resu = q.setMaxResults(10).getResultList();
        return resu;
    }
}
