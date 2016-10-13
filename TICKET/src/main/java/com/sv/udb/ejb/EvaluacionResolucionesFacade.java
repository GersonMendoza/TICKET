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
        Query q = getEntityManager().createQuery("SELECT er FROM EvaluacionResoluciones er INNER JOIN ResolucionSolicitudes rs ON er.codiResoSoli.codiResoSoli = rs.codiResoSoli INNER JOIN Solicitudes s ON s.codiSoli = rs.codiSoli.codiSoli WHERE er.estaEvalReso = " + true + " AND s.codiUsua = " + LoginBean.codiUsua, EvaluacionResoluciones.class);
        List resu = q.getResultList();
        return resu;
    }
}
